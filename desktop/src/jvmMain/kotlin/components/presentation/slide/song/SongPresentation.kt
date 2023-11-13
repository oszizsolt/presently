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
fun SongPresentation(
    slide: SongSlide?,
    presentationMode: PresentationMode,
    backgroundColor: Color = Color.Black,
    fontColor: Color = Color.White,
    fontSize: TextUnit = 25.sp,
    modifier: Modifier = Modifier,
) {
    if (presentationMode == PresentationMode.Hidden) {
        return
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
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

    }
}

@Preview
@Composable
fun SongPresentation_Preview() {
    PreviewContainer {
        Box(
            modifier = Modifier
                .width(800.dp)
                .height(500.dp)
        ) {
            SongPresentation(
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
                )
            )
        }
    }
}