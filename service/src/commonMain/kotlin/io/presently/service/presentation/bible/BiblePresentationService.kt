package io.presently.service.presentation.bible

import io.presently.service.bible.BibleBook
import io.presently.service.bible.BibleSlide
import io.presently.service.bible.BibleTranslation
import io.presently.service.engine.PresentationMode
import kotlinx.coroutines.flow.Flow

interface BiblePresentationService {

    fun activeSlide(): Flow<BibleSlide?>

    fun selectedSlide(): Flow<BibleSlide?>


    fun setSlide(slide: BibleSlide?)


    fun setMode(mode: PresentationMode)

    fun mode(): Flow<PresentationMode>

    fun translations(): List<BibleTranslation>

    fun books(translationId: String): List<BibleBook>

    fun slides(translationId: String): List<BibleSlide>

}