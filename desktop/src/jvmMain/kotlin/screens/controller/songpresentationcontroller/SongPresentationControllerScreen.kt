package screens.controller.songpresentationcontroller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.list.listitem.ListItem
import components.list.listitem.SelectedState
import components.panel.PanelItem
import components.panel.PanelLayout
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.presentation.PresentationMode
import screens.controller.songpresentationcontroller.viewmodel.SongControllerViewModel
import screens.controller.songpresentationcontroller.viewmodel.SongPresentationModeViewModel
import screens.controller.songpresentationcontroller.viewmodel.SongSlideControllerViewModel

@ExperimentalFoundationApi
@Composable
fun SongPresentationControllerScreen(
    songSlideControllerViewModel: SongSlideControllerViewModel,
    songPresentationModeViewModel: SongPresentationModeViewModel,
    songControllerViewModel: SongControllerViewModel
) {
    Row(
        Modifier
            .fillMaxSize()
    ) {
        val leftPanels = listOf(
            PanelItem(
                iconResource = "icons/bars-solid.svg",
                panelName = "Songs"
            ) {
                Column {
                    val songs by songControllerViewModel.songs().collectAsState(initial = emptyList())

                    val selectedSongId by songControllerViewModel.songId.collectAsState(initial = null)

                    songs.map { song ->
                        Row {
                            Label(
                                song.title,
                                modifier = Modifier
                                    .height(32.dp)
                                    .fillMaxWidth()
                                    .background(
                                        if (selectedSongId == song.id) {
                                            Color.Blue
                                        } else {
                                            Color.Transparent
                                        }
                                    )
                                    .onClick {
                                        songControllerViewModel.setSong(song)
                                    }
                            )
                        }
                    }
                }
            }
        )

        val rightPanels = listOf(
            PanelItem(
                iconResource = "icons/bars-solid.svg",
                panelName = "Outputs"
            ) {
                Column {
                    val currentSlide by songSlideControllerViewModel.active().collectAsState(initial = null)
                    val currentMode by songPresentationModeViewModel.mode().collectAsState(initial = PresentationMode.Normal)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .background(Color.Black)
                    ) {
                        Column(
                            Modifier.fillMaxWidth(),
                        ) {
                            if (currentMode != PresentationMode.Hidden) {
                                currentSlide?.let { currentSlide ->
                                    currentSlide.lines.forEach { line ->
                                        Row {
                                            Label(
                                                line,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    }

                    Row(
                        Modifier
                            .padding(all = 4.dp)
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .background(Color.Black)
                    ) {
                        Column {
                            Row(
                                Modifier
                                    .padding(top = 2.dp)
                                    .padding(bottom = 8.dp)
                            ) {
                                Column {
                                    currentSlide?.let { currentSlide ->
                                        currentSlide.lines.forEach { line ->
                                            Row {
                                                Label(line)
                                            }
                                        }
                                    }
                                }
                            }
                        }


                    }
                }
            }
        )

        PanelLayout(
            modifier = Modifier,
            leftPanels = leftPanels,
            rightPanels = rightPanels,
        ) {
            Column {

                val selectedSong by songControllerViewModel.song.collectAsState(initial = null)
                val selectedSlide by songSlideControllerViewModel.selected().collectAsState(initial = null)
                val currentSlide by songSlideControllerViewModel.active().collectAsState(initial = null)
                val currentMode by songPresentationModeViewModel.mode()
                    .collectAsState(initial = PresentationMode.Normal)

                selectedSong?.slides?.forEach { slide ->

                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        selectedState = when {
                            currentMode == PresentationMode.Hidden && currentSlide?.id == slide.id -> SelectedState.SecondarySelected
                            currentSlide?.id == slide.id -> SelectedState.PrimarySelected
                            selectedSlide?.id == slide.id -> SelectedState.TernarySelected
                            else -> SelectedState.NotSelected
                        },
                        {
                            songSlideControllerViewModel.setSlide(slide.id)
                        },
                        slide.lines[0] + "\n" + slide.lines[1],
                        slide.songId
                    )


                }
            }
        }
    }
}
