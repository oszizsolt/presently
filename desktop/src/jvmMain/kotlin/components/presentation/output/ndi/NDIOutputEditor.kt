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
import java.awt.GraphicsEnvironment

@Composable
fun NDIOutputEditor(
    config: NDIPresentationOutputConfig,
    onConfigChange: suspend (NDIPresentationOutputConfig) -> Unit
) {
    KeyValueEditor(
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