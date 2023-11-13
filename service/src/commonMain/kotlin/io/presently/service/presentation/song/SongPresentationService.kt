package io.presently.service.presentation.song

import io.presently.service.engine.PresentationMode
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import kotlinx.coroutines.flow.Flow


interface SongPresentationService {

    fun activeSlide(): Flow<SongSlide?>

    fun selectedSlide(): Flow<SongSlide?>


    fun nextSlides(howMany: Int): Flow<List<SongSlide>>


    fun songs(): Flow<List<Song>>

    fun currentSong(): Flow<Song?>


    fun setSlide(slideId: String?)

    fun setMode(mode: PresentationMode)
    fun mode(): Flow<PresentationMode>

    fun title(): String

}