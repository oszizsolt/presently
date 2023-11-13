package screens.controller.songpresentationcontroller.viewmodel

import io.presently.service.presentation.song.SongPresentationService
import io.presently.service.song.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class SongListControllerViewModel(
    private val coroutineScope: CoroutineScope,
    private val songPresentationService: SongPresentationService,
) {

    fun title(): String = songPresentationService.title()

}