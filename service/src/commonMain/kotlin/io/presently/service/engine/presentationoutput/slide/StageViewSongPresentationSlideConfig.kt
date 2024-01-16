package io.presently.service.engine.presentationoutput.slide

data class StageViewSongPresentationSlideConfig(
    val font: String?,
    val previewFont: String?,
    val fontSize: Int,
    val previewFontSize: Int,
    val fontColor: Long,
    val previewFontColor: Long,
    val backgroundColor: Long,
) : PresentationSlideConfig