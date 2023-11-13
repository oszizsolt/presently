package screens.controller.biblepresentationcontroller

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.SegmentedButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.engine.PresentationMode
import screens.controller.biblepresentationcontroller.viewmodel.BiblePresentationModeViewModel
import screens.controller.biblepresentationcontroller.viewmodel.BibleSlideViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BiblePresentationControllerWindow(
    biblePresentationModeViewModel: BiblePresentationModeViewModel,
    bibleSlideViewModel: BibleSlideViewModel,
    onClose: () -> Unit,
) {

    val presentationMode by biblePresentationModeViewModel.mode().collectAsState(PresentationMode.Normal)

    JBWindow(
        theme = DarkTheme,
        title = "Presently - Bible",
        onCloseRequest = onClose,
        onPreviewKeyEvent = {
            when {
                (it.key == Key.H && it.type == KeyEventType.KeyUp) -> {
                    val newMode = if (presentationMode == PresentationMode.Hidden) {
                        PresentationMode.Normal
                    } else {
                        PresentationMode.Hidden
                    }

                    biblePresentationModeViewModel.setMode(newMode)

                    true
                }

                (it.key == Key.F && it.type == KeyEventType.KeyUp) -> {

                    val newMode = if (presentationMode == PresentationMode.Frozen) {
                        PresentationMode.Normal
                    } else {
                        PresentationMode.Frozen
                    }

                    biblePresentationModeViewModel.setMode(newMode)

                    true
                }

                (it.key == Key.N && it.type == KeyEventType.KeyUp) -> {
                    biblePresentationModeViewModel.setMode(PresentationMode.Normal)

                    true
                }

                (it.key == Key.DirectionUp && it.type == KeyEventType.KeyDown) -> {

                    bibleSlideViewModel.jumpToPrevious()

                    true
                }

                (it.key == Key.DirectionDown && it.type == KeyEventType.KeyDown) -> {

                    bibleSlideViewModel.jumpToNext()

                    true
                }

                else -> false
            }
        },
        mainToolBar = {
            Row(
                Modifier
                    .padding(end = 12.dp)
                    .mainToolBarItem(Alignment.End)
            ) {
                val selectedIndex = when (presentationMode) {
                    PresentationMode.Normal -> 0
                    PresentationMode.Frozen -> 1
                    PresentationMode.Hidden -> 2
                }

                SegmentedButton(3, selectedIndex, {
                    val newMode = when (it) {
                        0 -> PresentationMode.Normal
                        1 -> PresentationMode.Frozen
                        2 -> PresentationMode.Hidden
                        else -> PresentationMode.Normal
                    }

                    biblePresentationModeViewModel.setMode(newMode)
                }) {
                    when (it) {
                        0 -> Label("Normal")
                        1 -> Label("Frozen")
                        2 -> Label("Hidden")
                        else -> Label("")
                    }
                }
            }
        }
    ) {
        BiblePresentationScreen(
            biblePresentationModeViewModel = biblePresentationModeViewModel,
            bibleSlideViewModel = bibleSlideViewModel,
        )
    }
}

