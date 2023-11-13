package io.presently.service.engine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PresentationEngine {

    fun setSlide(slide: Slide?, preview: Slide?)

    fun setPresentationMode(presentationMode: PresentationMode)


    val presentationMode: StateFlow<PresentationMode>

    val outputs: StateFlow<List<Unit>>

}