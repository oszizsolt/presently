package io.presently.service.engine.presentationoutput.output

import kotlinx.serialization.Serializable

@Serializable
data class NDIPresentationOutputConfig(
    val name: String,
    val height: Int,
    val width: Int,
) : PresentationOutputConfig {
    override val title = "NDI"
}