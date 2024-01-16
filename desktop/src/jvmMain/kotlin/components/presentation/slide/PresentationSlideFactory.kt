package components.presentation.slide

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import components.presentation.slide.bible.BiblePresentation
import components.presentation.slide.song.SongPresentation
import components.presentation.slide.song.StageViewSongPresentation
import io.presently.service.bible.BibleSlide
import io.presently.service.engine.PresentationMode
import io.presently.service.engine.Slide
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig
import io.presently.service.song.SongSlide

@Composable
fun PresentationSlideFactory(
    slide: Slide?,
    nextSlide: Slide?,
    presentationMode: PresentationMode,
    config: PresentationSlideConfig,
    modifier: Modifier = Modifier,
) {
    when (config) {
        is BiblePresentationSlideConfig -> BiblePresentation(
            slide = slide as? BibleSlide,
            presentationMode = presentationMode,
            backgroundColor = Color(config.backgroundColor),
            fontColor = Color(config.fontColor),
            fontSize = with(LocalDensity.current) {
                config.fontSize.toSp()
            },
            fontFamily = config.font,
            verseFontColor = Color(config.verseFontColor),
            verseFontSize = with(LocalDensity.current) {
                config.verseFontSize.toSp()
            },
            verseFontFamily = config.verseFont,
            modifier = modifier,
        )

        is SongPresentationSlideConfig -> SongPresentation(
            slide = slide as? SongSlide,
            presentationMode = presentationMode,
            backgroundColor = Color(config.backgroundColor),
            fontColor = Color(config.fontColor),
            fontSize = with(LocalDensity.current) {
                config.fontSize.toSp()
            },
            fontFamily = config.font,
            modifier = modifier,
        )

        is StageViewSongPresentationSlideConfig -> StageViewSongPresentation(
            slide = slide as? SongSlide,
            previewSlide = nextSlide as? SongSlide,
            presentationMode = presentationMode,
            backgroundColor = Color(config.backgroundColor),
            fontColor = Color(config.fontColor),
            previewFontColor = Color(config.previewFontColor),
            fontSize = with(LocalDensity.current) {
                config.fontSize.toSp()
            },
            previewFontSize = with(LocalDensity.current) {
                config.previewFontSize.toSp()
            },
            fontFamily = config.font,
            previewFontFamily = config.previewFont,
            modifier = modifier,
        )
    }
}