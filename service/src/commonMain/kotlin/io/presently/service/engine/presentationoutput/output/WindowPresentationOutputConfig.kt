package io.presently.service.engine.presentationoutput.output

import kotlinx.serialization.Serializable

@Serializable
data class WindowPresentationOutputConfig(
    val width: Int,
    val height: Int,
    val resizable: Boolean,
) : PresentationOutputConfig {
    override val title = "Windowed"
}



