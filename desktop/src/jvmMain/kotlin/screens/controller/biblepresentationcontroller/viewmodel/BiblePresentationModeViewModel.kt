package screens.controller.biblepresentationcontroller.viewmodel

import io.presently.service.presentation.PresentationMode
import io.presently.service.presentation.bible.BiblePresentationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class BiblePresentationModeViewModel(
    private val coroutineScope: CoroutineScope,
    private val biblePresentationService: BiblePresentationService,
) {

    fun mode(): Flow<PresentationMode> {
        return biblePresentationService.mode()
    }

    fun setMode(mode: PresentationMode) {
        biblePresentationService.setMode(mode)
    }

}