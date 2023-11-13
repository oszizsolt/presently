package components.presentation.slide.song

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.preview.PreviewContainer
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.engine.PresentationMode
import io.presently.service.song.SongSlide
import io.presently.service.song.SongSlideType

@Composable
fun PreviewSongPresentation(
    slide: SongSlide?,
    previewSlide: SongSlide?,
    presentationMode: PresentationMode,
    backgroundColor: Color = Color.Black,
    fontColor: Color = Color.White,
    previewFontColor: Color = Color.Yellow,
    fontSize: TextUnit = 25.sp,
    previewFontSize: TextUnit = 20.sp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
    ) {

        Label(
            modifier = Modifier.align(Alignment.TopStart),
            text = when (presentationMode) {
                PresentationMode.Normal -> ""
                PresentationMode.Hidden -> "H"
                PresentationMode.Frozen -> "F"
            },
            color = previewFontColor,
            fontSize = 90.sp,
        )


        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                slide?.lines?.forEach { line ->
                    Label(
                        text = line,
                        color = fontColor,
                        fontSize = fontSize,
                    )
                }
            }

            Box(Modifier.height(100.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                previewSlide?.lines?.forEach { line ->
                    Label(
                        text = line,
                        color = previewFontColor,
                        fontSize = previewFontSize,
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewSongPresentation_Preview() {
    PreviewContainer {
        Box(
            modifier = Modifier
                .width(800.dp)
                .height(500.dp)
        ) {
            PreviewSongPresentation(
                presentationMode = PresentationMode.Normal,
                slide = SongSlide(
                    id = "1",
                    lines = listOf(
                        "A csillagok fölött",
                        "Király él, az örök",
                    ),
                    type = SongSlideType.Normal,
                    groupId = "1",
                    songId = "1",
                ),
                previewSlide = SongSlide(
                    id = "2",
                    lines = listOf(
                        "Hangja, mint tenger zúg",
                        "Szava sziklákat zúz",
                    ),
                    type = SongSlideType.Normal,
                    groupId = "1",
                    songId = "1",
                )
            )
        }
    }
}