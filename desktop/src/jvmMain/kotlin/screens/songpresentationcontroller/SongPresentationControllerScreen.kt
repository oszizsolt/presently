package screens.songpresentationcontroller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.onHover
import io.presently.service.presentation.PresentationMode
import io.presently.service.presentation.song.SongPresentationService

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@Composable
fun SongPresentationControllerScreen(
    songPresentationService: SongPresentationService,
    songPresentationControllerViewModel: SongPresentationControllerViewModel
) {
    Row(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier.width(120.dp)
        ) {
            val songs by songPresentationService.songs().collectAsState(initial = emptyList())

            val selectedSongId by songPresentationControllerViewModel.songId.collectAsState(initial = null)

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
                                songPresentationControllerViewModel.setSong(song)
                            }
                    )
                }
            }
        }
        Column(
            Modifier.weight(1.0f)
        ) {

            val selectedSong by songPresentationControllerViewModel.song.collectAsState(initial = null)
            val selectedSlide by songPresentationService.selectedSlide().collectAsState(initial = null)
            val currentSlide by songPresentationService.currentSlide().collectAsState(initial = null)
            val currentMode by songPresentationService.mode().collectAsState(initial = PresentationMode.Normal)

            selectedSong?.slides?.forEach { slide ->
                var hover by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onHover {
                            hover = it
                        }
                        .background(
                            when {
                                currentMode == PresentationMode.Hidden && currentSlide?.id == slide.id -> Color.Blue.copy(red = 0.5f, green = 0.5f)
                                currentSlide?.id == slide.id -> Color.Blue
                                selectedSlide?.id == slide.id -> Color.Gray
                                hover -> Color.DarkGray
                                else -> Color.Transparent
                            }
                        )
                        .onClick {
                            songPresentationService.setSlide(slide.id)
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Column {
                        slide.lines.forEach { line ->
                            Row {
                                Label(line)
                            }
                        }
                    }

                }
            }
        }
        Column(
            Modifier
                .width(256.dp)
        ) {
            val currentSlide by songPresentationService.currentSlide().collectAsState(initial = null)
            val nextSlides by songPresentationService.nextSlides(howMany = 2).collectAsState(initial = emptyList())
            val currentMode by songPresentationService.mode().collectAsState(initial = PresentationMode.Normal)

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

                    nextSlides.forEach { slide ->
                        Row(
                            Modifier
                                .padding(vertical = 2.dp)
                        ) {
                            Column {
                                slide.lines.forEach { line ->
                                    Row {
                                        Label(
                                            line,
                                            fontSize = 9.sp,
                                            color = Color.Gray,
                                        )
                                    }
                                }
                            }

                        }

                    }
                }


            }
        }
    }
}