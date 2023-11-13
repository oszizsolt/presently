package screens.songpresentationcontroller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
import components.panel.PanelItem
import components.panel.PanelItemData
import components.panel.PanelLayout
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.SegmentedButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.presentation.PresentationMode
import screens.songpresentationcontroller.viewmodel.SongControllerViewModel
import screens.songpresentationcontroller.viewmodel.SongListControllerViewModel
import screens.songpresentationcontroller.viewmodel.SongPresentationModeViewModel
import screens.songpresentationcontroller.viewmodel.SongSlideControllerViewModel
import java.awt.Color

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@Composable
fun ApplicationScope.SongPresentationControllerWindow(
    songSlideControllerViewModel: SongSlideControllerViewModel,
    songPresentationModeViewModel: SongPresentationModeViewModel,
    songControllerViewModel: SongControllerViewModel,
    songListControllerViewModel: SongListControllerViewModel,
) {
    val currentMode by songPresentationModeViewModel.mode().collectAsState(initial = PresentationMode.Normal)
    val title = songListControllerViewModel.title()

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

                    songPresentationModeViewModel.setMode(newMode)

                    true
                }

                (it.key == Key.F && it.type == KeyEventType.KeyUp) -> {

                    val newMode = if (currentMode == PresentationMode.Frozen) {
                        PresentationMode.Normal
                    } else {
                        PresentationMode.Frozen
                    }

                    songPresentationModeViewModel.setMode(newMode)

                    true
                }

                (it.key == Key.N && it.type == KeyEventType.KeyUp) -> {
                    songPresentationModeViewModel.setMode(PresentationMode.Normal)

                    true
                }

                (it.key == Key.DirectionUp && it.type == KeyEventType.KeyDown) -> {

                    songSlideControllerViewModel.jumpToPrevious()

                    true
                }

                (it.key == Key.DirectionDown && it.type == KeyEventType.KeyDown) -> {

                    songSlideControllerViewModel.jumpToNext()

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
                val presentationMode by songPresentationModeViewModel.mode()
                    .collectAsState(initial = PresentationMode.Normal)

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

                    songPresentationModeViewModel.setMode(newMode)
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
        val panelList = mutableListOf<PanelItemData>()
        val panel1 = PanelItemData(
            iconResource = "icons/bars-solid.svg",
            panelName ="Panel 1"
        ) {
            Column(
                modifier = Modifier
                    .background(androidx.compose.ui.graphics.Color.Blue)
                    .fillMaxSize()
            ){
                Label(
                    modifier = Modifier
                        .background(androidx.compose.ui.graphics.Color.DarkGray),
                    text = "This is Panel 1\ncontent"
                )
            }
        }
        val panel2 = PanelItemData(
            iconResource = "icons/bars-solid.svg",
            panelName ="Panel 2"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(androidx.compose.ui.graphics.Color.Yellow)
            ){
                Label(
                    modifier = Modifier
                        .background(androidx.compose.ui.graphics.Color.DarkGray),
                    text = "This is Panel 2\ncontent"
                )
            }
        }
        panelList.add(panel1)
        panelList.add(panel2)

        PanelLayout(
            modifier = Modifier,
            leftPanels = panelList,
            rightPanels = panelList,
        ) {
            SongPresentationControllerScreen(
                songSlideControllerViewModel = songSlideControllerViewModel,
                songPresentationModeViewModel = songPresentationModeViewModel,
                songControllerViewModel = songControllerViewModel,
            )
        }
    }
}