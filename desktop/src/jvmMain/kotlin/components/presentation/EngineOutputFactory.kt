package components.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.presentation.output.PresentationOutputFactory
import components.presentation.slide.PresentationSlideFactory
import io.presently.service.engine.PresentationMode
import io.presently.service.engine.Slide
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig

@Composable
fun EngineOutputFactory(
    slide: Slide?,
    nextSlide: Slide?,
    presentationMode: PresentationMode,
    outputConfig: PresentationOutputConfig,
    slideConfig: PresentationSlideConfig,
    modifier: Modifier = Modifier,
) {
    PresentationOutputFactory(
        config = outputConfig,
    ) {
        PresentationSlideFactory(
            slide = slide,
            nextSlide = nextSlide,
            presentationMode = presentationMode,
            config = slideConfig,
            modifier = modifier,
        )
    }
}