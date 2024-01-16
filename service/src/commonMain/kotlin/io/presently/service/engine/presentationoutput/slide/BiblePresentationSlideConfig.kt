package io.presently.service.engine.presentationoutput.slide

data class BiblePresentationSlideConfig(
    val font: String?,
    val fontSize: Int,
    val fontColor: Long,
    val backgroundColor: Long,
    val verseFontColor: Long,
    val verseFontSize: Int,
    val verseFont: String?,
) : PresentationSlideConfig