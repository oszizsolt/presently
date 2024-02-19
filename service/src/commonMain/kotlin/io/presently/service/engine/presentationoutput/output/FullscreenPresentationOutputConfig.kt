package io.presently.service.engine.presentationoutput.output

import kotlinx.serialization.Serializable

@Serializable
data class FullscreenPresentationOutputConfig(
    val displayId: String?,
) : PresentationOutputConfig {
    override val title = "Fullscreen"
}



