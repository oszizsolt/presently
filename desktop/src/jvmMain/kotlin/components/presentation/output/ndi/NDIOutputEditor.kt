package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.IntKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import components.presentation.editor.keyvalueeditor.StringKeyValueEditorValueType
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig

@Composable
fun NDIOutputEditor(
    config: NDIPresentationOutputConfig,
    onConfigChange: suspend (NDIPresentationOutputConfig) -> Unit
) {
    KeyValueEditor(
        title = "${config.title} Settings",
        config = mapOf(
            "Name" to StringKeyValueEditorValueType(
                canBeBlank = false,
            ),
            "Width" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
            "Height" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
        ),
        initialValues = mapOf(
            "Name" to config.name,
            "Width" to config.width.toString(),
            "Height" to config.height.toString(),
        ),
        onSave = {
            onConfigChange(
                NDIPresentationOutputConfig(
                    name = it["Name"]!!,
                    width = it["Width"]!!.toInt(),
                    height = it["Height"]!!.toInt(),
                )
            )
        }
    )

}