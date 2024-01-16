package io.presently.service.config


data class Config(
    val name: String,
    val outputs: List<Output>
)

data class Output(
    val name: String,
    val config: OutputConfig,
    val views: List<PresentationView>
)

data class PresentationView(
    val name: String,
    val config: PresentationViewConfig
)


interface OutputConfig {
    val isEnabled: Boolean
}

interface PresentationViewConfig {
    val isEnabled: Boolean
}

interface ConfigService {
    fun get(): List<Config>

    fun delete(name: String)

    fun put(config: Config)

}