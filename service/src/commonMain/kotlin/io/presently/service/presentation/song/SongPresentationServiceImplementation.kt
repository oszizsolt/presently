package io.presently.service.presentation.song

import io.presently.service.list.SongList
import io.presently.service.presentation.PresentationMode
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SongPresentationServiceImplementation(
    private val list: SongList
) : SongPresentationService {

    private val slidesByIds: Map<String, SongSlide> = list.songs
        .flatMap { it.slides }
        .associateBy { it.id }

    private val songsByIds: Map<String, Song> = list.songs.associateBy { it.id }

    private val slides: List<SongSlide> = list.songs
        .flatMap { it.slides }

    private val mode: MutableStateFlow<PresentationMode> = MutableStateFlow(PresentationMode.Normal)

    private val currentSlideId: MutableStateFlow<String?> = MutableStateFlow(null)
    private val selectedSlideId: MutableStateFlow<String?> = MutableStateFlow(null)

    private val currentSlide: Flow<SongSlide?> = currentSlideId.map { currentSlideId ->
        slidesByIds[currentSlideId]
    }

    private val selectedSlide: Flow<SongSlide?> = selectedSlideId.map { selectedSlideId ->
        slidesByIds[selectedSlideId]
    }

    override fun currentSlide(): Flow<SongSlide?> {
        return currentSlide
    }

    override fun selectedSlide(): Flow<SongSlide?> {
        return selectedSlide
    }

    override fun nextSlides(howMany: Int): Flow<List<SongSlide>> {
        return currentSlideId.map { currentSlideId ->
            val index = slides.indexOfFirst { it.id == currentSlideId }

            if (index < 0) {
                return@map emptyList<SongSlide>()
            }

            if (index == slides.size - 1) {
                return@map emptyList()
            }

            slides.subList(index + 1, (index + 1 + howMany).coerceAtMost(slides.size - 1))
        }
    }

    override fun songs(): Flow<List<Song>> {
        return flow {
            emit(list.songs)
        }
    }

    override fun currentSong(): Flow<Song?> {
        return currentSlide.map {
            val songId = it?.songId ?: return@map null

            songsByIds[songId]
        }
    }

    override fun setSlide(slideId: String?) {
        selectedSlideId.value = slideId

        if (this.mode.value != PresentationMode.Frozen) {
            currentSlideId.value = slideId
        }
    }

    override fun skipSlides(delta: Int) {
        if (delta == 0) {
            return
        }

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

        setSlide(slideIdToSkipTo)
    }

    override fun setMode(mode: PresentationMode) {
        val oldMode = this.mode.value

        this.mode.value = mode

        if (oldMode == PresentationMode.Frozen) {
            currentSlideId.value = selectedSlideId.value
        }
    }

    override fun mode(): Flow<PresentationMode> {
        return mode
    }

    override fun title(): String {
        return list.title
    }
}