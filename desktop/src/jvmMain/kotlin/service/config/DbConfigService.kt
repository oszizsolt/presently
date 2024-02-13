package service.config

import io.presently.service.config.Config
import io.presently.service.config.ConfigService
import io.presently.service.engine.presentationoutput.OutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.util.prefs.Preferences

class DbConfigService : ConfigService {

    private val path = run {
        val preferences = Preferences.userRoot().node(javaClass.name)
        val path = preferences.get(
            "config_db",
            kotlin.io.path.Path(System.getProperty("user.home"), "Presently", "config.db").toString()
        )
        val configFile = File(path)
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            configFile.createNewFile()
        }
        println(path)
        path
    }

    // Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")

//    private val db = Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
    private val db = Database.connect("jdbc:sqlite:$path", "org.sqlite.JDBC")
        .apply {
//        transactionManager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        transaction(db = this) {
            SchemaUtils.create(ConfigTable, ConfigOutputTable)
        }
    }

    override suspend fun get(): List<Config> {
        return newSuspendedTransaction(db = db) {
            val outputsByConfigNames = ConfigOutputTable
                .selectAll()
                .groupBy { it[ConfigOutputTable.configName] }
                .mapValues { (key, rows) ->
                    rows.map {
                        OutputConfig(
                            name = it[ConfigOutputTable.name],
                            outputConfig = Json.decodeFromString<PresentationOutputConfig>(it[ConfigOutputTable.outputConfigJson]),
                            slideConfig = Json.decodeFromString<PresentationSlideConfig>(it[ConfigOutputTable.slideConfigJson]),
                        )
                    }
                }

            ConfigTable.selectAll()
                .map {
                    val name = it[ConfigTable.name]

                    Config(
                        name = name,
                        outputs = outputsByConfigNames[name] ?: emptyList()
                    )
                }
        }
    }

    override suspend fun delete(name: String) {
        newSuspendedTransaction(db = db) {
            ConfigOutputTable.deleteWhere { ConfigOutputTable.configName eq name }
            ConfigTable.deleteWhere { ConfigTable.name eq name }
        }
    }

    override suspend fun put(config: Config) {
        newSuspendedTransaction {
            ConfigOutputTable.deleteWhere { ConfigOutputTable.configName eq config.name }
            ConfigTable.deleteWhere { ConfigTable.name eq config.name }

            ConfigTable.insert {
                it[ConfigTable.name] = config.name
            }

            ConfigOutputTable.batchInsert(config.outputs) { output ->
                this[ConfigOutputTable.configName] = config.name
                this[ConfigOutputTable.name] = output.name
                this[ConfigOutputTable.slideConfigJson] = Json.encodeToString(output.slideConfig)
                this[ConfigOutputTable.outputConfigJson] = Json.encodeToString(output.outputConfig)
            }
        }
    }
}
