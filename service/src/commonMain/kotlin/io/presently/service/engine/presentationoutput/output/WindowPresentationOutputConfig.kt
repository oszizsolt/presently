package io.presently.service.engine.presentationoutput.output

data class WindowPresentationOutputConfig(
    val resolution: Resolution?,
) : PresentationOutputConfig {
    sealed interface Resolution

    data class FixedResolution(
        val width: Int,
        val height: Int,
        val resizable: Boolean,
    ) : Resolution

    data class Fullscreen(
        val displayId: String
    ) : Resolution
}



