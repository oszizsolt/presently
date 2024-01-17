package service.config

import org.jetbrains.exposed.sql.Table

object ConfigOutputTable : Table("config_output") {

    val configName = varchar("config_name", 255).references(ConfigTable.name)

    val name = varchar("name", 255)

    val outputConfigJson = text("output_config_json")
    val slideConfigJson = text("slide_config_json")

    override val primaryKey: PrimaryKey = PrimaryKey(columns = arrayOf(configName, name))
}