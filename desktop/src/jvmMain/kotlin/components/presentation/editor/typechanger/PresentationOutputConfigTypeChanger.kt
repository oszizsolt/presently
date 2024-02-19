package components.presentation.editor.typechanger

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.presentation.editor.keyvalueeditor.EnumKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import components.presentation.output.window.FullscreenOutput
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig

@Composable
fun PresentationOutputConfigTypeChanger(
    current: PresentationOutputConfig,
    onChange: suspend (PresentationOutputConfig) -> Unit
) {

    val titles = listOf(
        "Fullscreen",
        "NDI",
        "Windowed",
    )

    KeyValueEditor(
        title = "Select Output Type",
        config = mapOf(
            "Type" to EnumKeyValueEditorValueType(
                values = titles.associateWith { it!! }
            )
        ),
        initialValues = mapOf(
            "Type" to current.title,
        ),
        onSave = {
            val value = it["Type"]!!

            val newValue = when (value) {
                current.title -> current
                "Fullscreen" -> FullscreenPresentationOutputConfig(
                    displayId = null,
                )

                "Windowed" -> WindowPresentationOutputConfig(
                    width = 640,
                    height = 480,
                    resizable = true,
                )

                "NDI" -> NDIPresentationOutputConfig(
                    name = "Presently",
                    width = 1920,
                    height = 1080,
                )

                else -> current
            }

            if (newValue != current) {
                onChange(newValue)
            }
        }
    )
}