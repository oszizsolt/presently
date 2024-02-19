package io.presently.service.engine.presentationoutput.slide

import kotlinx.serialization.Serializable

@Serializable
data class SongPresentationSlideConfig(
    val font: String?,
    val fontSize: Int,
    val fontColor: Long,
    val backgroundColor: Long,
) : PresentationSlideConfig {
    override val title = "Song"
}