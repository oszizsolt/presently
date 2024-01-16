package io.presently.service.engine.presentationoutput

import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig

data class OutputConfig(
    val name: String,
    val outputConfig: PresentationOutputConfig,
    val slideConfig: PresentationSlideConfig,
)