package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.IntKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig

@Composable
fun BibleSlideEditor(
    config: BiblePresentationSlideConfig,
    onConfigChange: suspend (BiblePresentationSlideConfig) -> Unit
) {
    KeyValueEditor(
        title = "${config.title} Settings",
        config = mapOf(
//            "Font" to StringKeyValueEditorValueType(
//                canBeBlank = true,
//            ),
//            "Font Color" to IntKeyValueEditorValueType(
//                canBeNull = false,
//                min = 1,
//                max = null,
//            ),
            "Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
            "Verse Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
        ),
        initialValues = mapOf(
            "Font Size" to config.fontSize.toString(),
            "Verse Font Size" to config.verseFontSize.toString(),
        ),
        onSave = {
            onConfigChange(
                BiblePresentationSlideConfig(
                    font = null,
                    fontSize = it["Font Size"]!!.toInt(),
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                    verseFontColor = 0xFFFFFFFF,
                    verseFontSize = it["Verse Font Size"]!!.toInt(),
                    verseFont = null,
                )
            )
        }
    )

}