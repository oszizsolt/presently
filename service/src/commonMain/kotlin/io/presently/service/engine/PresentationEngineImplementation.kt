package io.presently.service.engine

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PresentationEngineImplementation(
    // TODO config
) : PresentationEngine {
    private val _presentationMode = MutableStateFlow(PresentationMode.Normal)

    private val currentSlide = MutableStateFlow<Slide?>(null)
    private val nextSlide = MutableStateFlow<Slide?>(null)

    override fun setSlide(slide: Slide?, preview: Slide?) {
        currentSlide.value = slide
        nextSlide.value = preview
    }

    override fun setPresentationMode(presentationMode: PresentationMode) {
        _presentationMode.value = presentationMode
    }

    override val presentationMode: StateFlow<PresentationMode>
        get() = _presentationMode.asStateFlow()

    override val outputs: StateFlow<List<Unit>>
        get() = MutableStateFlow(emptyList<Unit>()).asStateFlow()

}