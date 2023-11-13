package screens.controller.biblepresentationcontroller.viewmodel

import io.presently.service.bible.BibleSlide
import io.presently.service.bible.BibleTranslation
import io.presently.service.presentation.bible.BiblePresentationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class BibleSlideViewModel(
    private val coroutineScope: CoroutineScope,
    private val biblePresentationService: BiblePresentationService,
) {


    private val activeTranslation: MutableStateFlow<BibleTranslation?> =
        MutableStateFlow(biblePresentationService.translations().firstOrNull())

    private val activeSlides: StateFlow<List<BibleSlide>> = activeTranslation.map { translation ->
        translation?.let { translation ->
            biblePresentationService.slides(translation.id)
        } ?: emptyList()
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList(),
    )

    private val activeSlideId: StateFlow<String?> = biblePresentationService.activeSlide()
        .map { it?.id }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = null,
        )

    private val selectedSlideId: StateFlow<String?> = biblePresentationService.selectedSlide()
        .map { it?.id }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = null,
        )

    fun slides(): Flow<List<BibleSlide>> = activeSlides

    fun active(): Flow<BibleSlide?> {
        return biblePresentationService.activeSlide()
    }

    fun selected(): Flow<BibleSlide?> {
        return biblePresentationService.selectedSlide()
    }

    fun setSlide(slide: BibleSlide?) {
        biblePresentationService.setSlide(slide)
    }

    fun jumpToNext() {
        jump(1)
    }

    fun jumpToPrevious() {
        jump(-1)
    }

    private fun jump(delta: Int) {
        if (delta == 0) {
            return
        }

        val slides = activeSlides.value

        if (slides.isEmpty()) {
            return
        }

        val currentSlideId = selectedSlideId.value

        val currentIndex = slides.indexOfFirst { it.id == currentSlideId }

        val nextIndex = (currentIndex + delta)
            .coerceAtMost(slides.size - 1)
            .coerceAtLeast(0)

        val nextSlide = slides[nextIndex]

        setSlide(nextSlide)
    }

}