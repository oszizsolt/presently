package components.presentation.slide.bible

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.preview.PreviewContainer
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.bible.BibleSlide
import io.presently.service.engine.PresentationMode
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig

@Composable
fun BiblePresentation(
    slide: BibleSlide?,
    presentationMode: PresentationMode,
    backgroundColor: Color = Color.Black,
    fontColor: Color = Color.White,
    fontSize: TextUnit = 25.sp,
    fontFamily: String? = null,
    verseFontColor: Color = Color.White,
    verseFontSize: TextUnit = 20.sp,
    verseFontFamily: String? = null,
    modifier: Modifier = Modifier,
) {
    if (presentationMode == PresentationMode.Hidden) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor),
        )

        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            slide?.content?.let { content ->
                Label(
                    text = content,
                    color = fontColor,
                    fontSize = fontSize,
                    fontFamily = fontFamily?.let {
                        FontFamily(Font(it))
                    },
                )
            }

            Box(Modifier.height(50.dp))

            slide?.let { slide ->
                Label(
                    text = "${slide.book} ${slide.chapter}:${slide.verse}",
                    color = verseFontColor,
                    fontSize = verseFontSize,
                    fontFamily = verseFontFamily?.let {
                        FontFamily(Font(it))
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun BiblePresentation_Preview() {
    PreviewContainer {
        Box(
            modifier = Modifier
                .width(800.dp)
                .height(500.dp)
        ) {
            BiblePresentation(
                presentationMode = PresentationMode.Normal,
                slide = BibleSlide(
                    id = "1",
                    book = "János",
                    chapter = 1,
                    verse = 1,
                    content = "Kezdetben Vala az ige. Az ige volt Istennél, és Isten volt az ige"
                )
            )
        }
    }
}