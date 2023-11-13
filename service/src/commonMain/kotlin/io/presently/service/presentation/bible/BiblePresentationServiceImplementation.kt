package io.presently.service.presentation.bible

import io.presently.service.bible.BibleBook
import io.presently.service.bible.BibleSlide
import io.presently.service.bible.BibleTranslation
import io.presently.service.engine.PresentationEngine
import io.presently.service.engine.PresentationMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class BiblePresentationServiceImplementation(
    books: Map<BibleTranslation, List<BibleBook>>,
    private val engine: PresentationEngine,
) : BiblePresentationService {

    private val translations: List<BibleTranslation> = books.keys.toList()

    private val booksByTranslations: Map<String, List<BibleBook>> = books.mapKeys { (translation, _) ->
        translation.id
    }

    private val slidesByTranslationIds: Map<String, List<BibleSlide>> =
        books.entries.associate { (translation, books) ->
            translation.id to books.flatMap { book ->
                book.chapters.flatMap { chapter ->
                    chapter.verses.flatMap { verse ->
                        verse.slides
                    }
                }
            }
        }

    private val slidesByIds = books
        .flatMap { (_, books) ->
            books.flatMap { book ->
                book.chapters.flatMap { chapter ->
                    chapter.verses.flatMap { verse ->
                        verse.slides
                    }
                }
            }
        }
        .associateBy { it.id }

    private val activeSlideId: MutableStateFlow<String?> = MutableStateFlow(null)
    private val selectedSlideId: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun activeSlide(): Flow<BibleSlide?> {
        return activeSlideId.map { id ->
            id?.let { id ->
                slidesByIds[id]
            }
        }
    }

    override fun selectedSlide(): Flow<BibleSlide?> {
        return selectedSlideId.map { id ->
            id?.let { id ->
                slidesByIds[id]
            }
        }
    }

    override fun setSlide(slide: BibleSlide?) {
        val currentMode = engine.presentationMode.value

        selectedSlideId.value = slide?.id

        if (currentMode != PresentationMode.Frozen) {
            activeSlideId.value = slide?.id

            engine.setSlide(
                slide = slide,
                preview = null,
            )
        }
    }

    override fun setMode(mode: PresentationMode) {
        val oldMode = engine.presentationMode.value

        engine.setPresentationMode(mode)

        if (oldMode == PresentationMode.Frozen) {
            activeSlideId.value = selectedSlideId.value

            val slide = slidesByIds[selectedSlideId.value]

            engine.setSlide(
                slide = slide,
                preview = null,
            )
        }
    }

    override fun mode(): Flow<PresentationMode> {
        return engine.presentationMode
    }

    override fun translations(): List<BibleTranslation> {
        return translations
    }

    override fun books(translationId: String): List<BibleBook> {
        return booksByTranslations[translationId] ?: emptyList()
    }

    override fun slides(translationId: String): List<BibleSlide> {
        return slidesByTranslationIds[translationId] ?: emptyList()
    }
}