package io.presently.service.engine.presentationoutput.slide

data class SongPresentationSlideConfig(
    val font: String?,
    val fontSize: Int,
    val fontColor: Long,
    val backgroundColor: Long,
) : PresentationSlideConfig