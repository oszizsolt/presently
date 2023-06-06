package screens.songpresentationcontroller

import io.presently.service.song.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class SongPresentationControllerViewModel {

    private val selectedSong: MutableStateFlow<Song?> = MutableStateFlow(null)

    val song: Flow<Song?> = selectedSong

    val songId: Flow<String?> = selectedSong.map { it?.id }

    fun setSong(song: Song?) {
        selectedSong.value = song
    }



}