package components.presentation.output.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.presentation.editor.IntKeyValueEditorValueType
import components.presentation.editor.KeyValueEditor
import components.presentation.editor.KeyValueEditorValueType
import components.presentation.editor.StringKeyValueEditorValueType
import io.kanro.compose.jetbrains.expui.control.*
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import java.awt.GraphicsEnvironment

@Composable
fun SongSlideEditor(
    config: SongPresentationSlideConfig,
    onConfigChange: suspend (SongPresentationSlideConfig) -> Unit
) {
    KeyValueEditor(
        config = mapOf(
            "Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
        ),
        initialValues = mapOf(
            "Font Size" to config.fontSize.toString(),
        ),
        onSave = {
            onConfigChange(
                SongPresentationSlideConfig(
                    font = null,
                    fontSize = it["Font Size"]!!.toInt(),
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                )
            )
        }
    )

}