package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.IntKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig

@Composable
fun SongSlideEditor(
    config: SongPresentationSlideConfig,
    onConfigChange: suspend (SongPresentationSlideConfig) -> Unit
) {
    KeyValueEditor(
        title = "${config.title} Settings",
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