package io.presently.service.presentation.song

import io.presently.service.engine.PresentationEngine
import io.presently.service.list.SongList
import io.presently.service.engine.PresentationMode
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SongPresentationServiceImplementation(
    private val list: SongList,
    private val engine: PresentationEngine,
) : SongPresentationService {

    private val slidesByIds: Map<String, SongSlide> = list.songs
        .flatMap { it.slides }
        .associateBy { it.id }

    private val songsByIds: Map<String, Song> = list.songs.associateBy { it.id }

    private val slides: List<SongSlide> = list.songs
        .flatMap { it.slides }

    private val currentSlideId: MutableStateFlow<String?> = MutableStateFlow(null)
    private val selectedSlideId: MutableStateFlow<String?> = MutableStateFlow(null)

    private val currentSlide: Flow<SongSlide?> = currentSlideId.map { currentSlideId ->
        slidesByIds[currentSlideId]
    }

    private val selectedSlide: Flow<SongSlide?> = selectedSlideId.map { selectedSlideId ->
        slidesByIds[selectedSlideId]
    }

    override fun activeSlide(): Flow<SongSlide?> {
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

        if (engine.presentationMode.value != PresentationMode.Frozen) {
            currentSlideId.value = slideId

            val index = slides.indexOfFirst { it.id == slideId }

            val currentSlide = slides.getOrNull(index)
            val nextSlide = slides.getOrNull(index + 1)

            engine.setSlide(
                slide = currentSlide,
                preview = nextSlide,
            )
        }
    }

    override fun setMode(mode: PresentationMode) {
        val oldMode = engine.presentationMode.value

        engine.setPresentationMode(mode)

        if (oldMode == PresentationMode.Frozen) {
            setSlide(selectedSlideId.value)
//            currentSlideId.value = selectedSlideId.value
        }
    }

    override fun mode(): Flow<PresentationMode> {
        return engine.presentationMode
    }

    override fun title(): String {
        return list.title
    }
}