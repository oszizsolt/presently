package io.presently.service.presentation.song

import io.presently.service.presentation.PresentationMode
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import kotlinx.coroutines.flow.Flow


interface SongPresentationService {

    fun currentSlide(): Flow<SongSlide?>

    fun selectedSlide(): Flow<SongSlide?>


    fun nextSlides(): Flow<List<SongSlide>>


    fun songs(): Flow<List<Song>>

    fun currentSong(): Flow<Song?>



    fun setSlide(slide: SongSlide?)

    fun setMode(mode: PresentationMode)

}