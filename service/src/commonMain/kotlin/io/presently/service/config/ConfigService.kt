package io.presently.service.config

import io.presently.service.engine.presentationoutput.OutputConfig


data class Config(
    val name: String,
    val outputs: List<OutputConfig>
)

interface ConfigService {
    suspend fun get(): List<Config>

    suspend fun delete(name: String)

    suspend fun put(config: Config)
}