package io.presently.service.engine

import io.presently.service.engine.presentationoutput.OutputConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PresentationEngineImplementation(
    override val outputs: List<OutputConfig>,
) : PresentationEngine {
    private val _presentationMode = MutableStateFlow(PresentationMode.Normal)

    private val currentSlide = MutableStateFlow<Slide?>(null)
    private val previewSlide = MutableStateFlow<Slide?>(null)
    override val current: StateFlow<Slide?>
        get() = currentSlide.asStateFlow()
    override val preview: StateFlow<Slide?>
        get() = previewSlide.asStateFlow()

    override fun setSlide(slide: Slide?, preview: Slide?) {
        currentSlide.value = slide
        previewSlide.value = preview
    }

    override fun setPresentationMode(presentationMode: PresentationMode) {
        _presentationMode.value = presentationMode
    }

    override val presentationMode: StateFlow<PresentationMode>
        get() = _presentationMode.asStateFlow()

}