package io.presently.service.engine.presentationoutput.output

import kotlinx.serialization.Serializable

@Serializable
data class WindowPresentationOutputConfig(
    val resolution: Resolution?,
) : PresentationOutputConfig {

    @Serializable
    sealed interface Resolution

    @Serializable
    data class FixedResolution(
        val width: Int,
        val height: Int,
        val resizable: Boolean,
    ) : Resolution

    @Serializable
    data class Fullscreen(
        val displayId: String
    ) : Resolution
}



