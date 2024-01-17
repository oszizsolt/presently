package service.config

import org.jetbrains.exposed.sql.Table

object ConfigTable : Table("config") {

    val name = varchar("name", 255).uniqueIndex()

}