package screens.songpresentationcontroller.viewmodel

import io.presently.service.presentation.PresentationMode
import io.presently.service.presentation.song.SongPresentationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SongPresentationModeViewModel(
    private val coroutineScope: CoroutineScope,
    private val songPresentationService: SongPresentationService,
) {

    private val currentMode = songPresentationService
        .mode()
        .stateIn(
            coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = PresentationMode.Normal,
        )

    fun setMode(mode: PresentationMode) {
        songPresentationService.setMode(mode)
    }

    fun mode(): Flow<PresentationMode> {
        return currentMode
    }
}