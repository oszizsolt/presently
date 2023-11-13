package screens.controller.songpresentationcontroller.viewmodel

import io.presently.service.presentation.song.SongPresentationService
import io.presently.service.song.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class SongControllerViewModel(
    private val coroutineScope: CoroutineScope,
    private val songPresentationService: SongPresentationService,
) {

    private val selectedSong: MutableStateFlow<Song?> = MutableStateFlow(null)

    val song: Flow<Song?> = selectedSong

    fun songs(): Flow<List<Song>> = songPresentationService.songs()
    fun currentSong(): Flow<Song?> = songPresentationService.currentSong()

    val songId: Flow<String?> = selectedSong.map { it?.id }

    fun setSong(song: Song?) {
        selectedSong.value = song
    }

}