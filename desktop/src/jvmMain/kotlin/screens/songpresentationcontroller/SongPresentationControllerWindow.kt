package screens.songpresentationcontroller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
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
import io.presently.service.presentation.PresentationMode
import io.presently.service.presentation.song.SongPresentationService

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@Composable
fun ApplicationScope.SongPresentationControllerWindow(
    songPresentationService: SongPresentationService,
    songPresentationControllerViewModel: SongPresentationControllerViewModel,
) {
    val currentMode by songPresentationService.mode().collectAsState(initial = PresentationMode.Normal)
    val title = songPresentationService.title()

    JBWindow(
        theme = DarkTheme,
        title = "Presently - $title",
        onCloseRequest = ::exitApplication,
        onPreviewKeyEvent = {
            when {
                (it.key == Key.H && it.type == KeyEventType.KeyUp) -> {
                    val newMode = if (currentMode == PresentationMode.Hidden) {
                        PresentationMode.Normal
                    } else {
                        PresentationMode.Hidden
                    }

                    songPresentationService.setMode(newMode)

                    true
                }
                (it.key == Key.F && it.type == KeyEventType.KeyUp) -> {

                    val newMode = if (currentMode == PresentationMode.Frozen) {
                        PresentationMode.Normal
                    } else {
                        PresentationMode.Frozen
                    }

                    songPresentationService.setMode(newMode)

                    true
                }
                (it.key == Key.N && it.type == KeyEventType.KeyUp) -> {
                    songPresentationService.setMode(PresentationMode.Normal)

                    true
                }

                (it.key == Key.DirectionUp && it.type == KeyEventType.KeyDown) -> {

                    songPresentationService.skipSlides(delta = -1)

                    true
                }

                (it.key == Key.DirectionDown && it.type == KeyEventType.KeyDown) -> {

                    songPresentationService.skipSlides(delta = 1)

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
                    val presentationMode by songPresentationService.mode().collectAsState(initial = PresentationMode.Normal)

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

                        songPresentationService.setMode(newMode)
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
        SongPresentationControllerScreen(
            songPresentationService = songPresentationService,
            songPresentationControllerViewModel = songPresentationControllerViewModel,
        )
    }
}