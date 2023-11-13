package screens.controller.songpresentationcontroller.viewmodel

import io.presently.service.presentation.song.SongPresentationService
import io.presently.service.song.SongSlide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class SongSlideControllerViewModel(
    private val coroutineScope: CoroutineScope,
    private val songPresentationService: SongPresentationService,
) {

    private var slidesOfCurrentSong = songPresentationService
        .currentSong()
        .map { song ->
            song?.slides ?: emptyList()
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList(),
        )

    private var selectedSlideId = songPresentationService
        .selectedSlide()
        .map { slide ->
            slide?.id
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = null,
        )

    fun active(): Flow<SongSlide?> {
        return songPresentationService.activeSlide()
    }
    fun selected(): Flow<SongSlide?> {
        return songPresentationService.selectedSlide()
    }

    fun jumpToPrevious() {
        jump(-1)
    }

    fun jumpToNext() {
        jump(1)
    }

    fun setSlide(slideId: String?) {
        songPresentationService.setSlide(slideId)
    }

    private fun jump(delta: Int) {
        if (delta == 0) {
            return
        }

        val slides = slidesOfCurrentSong.value


        if (slides.isEmpty()) {
            return
        }

        val currentSlideIndex = slides.indexOfFirst { it.id == selectedSlideId.value }

        if (currentSlideIndex < 0) {
            return
        }

        var slideIndexToSkipTo = currentSlideIndex + delta

        if (slideIndexToSkipTo < 0) {
            slideIndexToSkipTo = 0
        }

        if (slideIndexToSkipTo >= slides.size) {
            slideIndexToSkipTo = slides.size - 1
        }

        val slideIdToSkipTo = slides[slideIndexToSkipTo].id

        songPresentationService.setSlide(slideIdToSkipTo)
    }


}