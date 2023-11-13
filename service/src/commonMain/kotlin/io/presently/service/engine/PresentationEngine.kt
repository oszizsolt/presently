package io.presently.service.engine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PresentationEngine {

    val current: StateFlow<Slide?>
    val preview: StateFlow<Slide?>

    fun setSlide(slide: Slide?, preview: Slide?)

    fun setPresentationMode(presentationMode: PresentationMode)


    val presentationMode: StateFlow<PresentationMode>

    val outputs: StateFlow<List<Unit>>

}