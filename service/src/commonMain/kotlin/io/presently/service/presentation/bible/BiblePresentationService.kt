package io.presently.service.presentation.bible

import io.presently.service.bible.BibleSlide
import io.presently.service.bible.BibleTranslation
import io.presently.service.presentation.PresentationMode
import kotlinx.coroutines.flow.Flow

interface BiblePresentationService {

    fun currentSlide(): Flow<BibleSlide?>

    fun selectedSlide(): Flow<BibleSlide?>


    fun translations(): Flow<List<BibleTranslation>>

    fun currentTranslation(): Flow<BibleTranslation?>

    fun setTranslation(id: String)

    fun setSlide(slide: BibleSlide?)

    fun setMode(mode: PresentationMode)


    fun addBookmark(slideId: String)
    fun bookmarks(): Flow<BibleSlide>


}