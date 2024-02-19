package io.presently.service.config

import io.presently.service.engine.presentationoutput.OutputConfig
import kotlinx.coroutines.flow.Flow


data class Config(
    val name: String,
    val outputs: List<OutputConfig>
)

interface ConfigService {
    fun get(): Flow<List<Config>>

    suspend fun delete(name: String)

    suspend fun put(config: Config)
}