package components.presentation.output.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import components.presentation.editor.EnumKeyValueEditorValueType
import components.presentation.editor.IntKeyValueEditorValueType
import components.presentation.editor.KeyValueEditor
import components.presentation.editor.StringKeyValueEditorValueType
import io.kanro.compose.jetbrains.expui.control.*
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import java.awt.GraphicsEnvironment

@Composable
fun WindowOutputEditor(
    config: WindowPresentationOutputConfig,
    onConfigChange: suspend (WindowPresentationOutputConfig) -> Unit
) {
    KeyValueEditor(
        config = mapOf(
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
            "Resizable" to EnumKeyValueEditorValueType(
                values = mapOf(
                    "No" to "No",
                    "Yes" to "Yes",
                )
            ),
        ),
        initialValues = mapOf(
            "Width" to config.width.toString(),
            "Height" to config.height.toString(),
            "Resizable" to if (config.resizable) {
                "Yes"
            } else {
                "No"
            },
        ),
        onSave = {
            onConfigChange(
                WindowPresentationOutputConfig(
                    width = it["Width"]!!.toInt(),
                    height = it["Height"]!!.toInt(),
                    resizable = it["Resizable"]!! == "Yes",
                )
            )
        }
    )

//    Column(
//        modifier = Modifier
//    ) {
//
//        var width by remember(config.width) { mutableStateOf<Int?>((config.width)) }
//        var height by remember(config.height) { mutableStateOf<Int?>((config.height)) }
//        var resizable by remember(config.resizable) {
//            mutableStateOf(config.resizable)
//        }
//
//        val save: (() -> Unit) -> Unit = { callback ->
//            callback()
//
//
//
//            onConfigChange(
//                WindowPresentationOutputConfig(
//                    width = width ?: 640,
//                    height = height ?: 480,
//                    resizable = resizable,
//                )
//            )
//        }
//
//
//        Box(Modifier.height(8.dp))
//
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Label(
//                text = "Width",
//                modifier = Modifier
//                    .width(50.dp)
//            )
//
//            TextField(
//                value = width?.toString() ?: "",
//                onValueChange = {
//                    save {
//                        if (it.isBlank()) {
//                            width = null
//                        } else if (it.toIntOrNull() != null) {
//                            width = it.toIntOrNull()
//                        }
//                    }
//                },
//            )
//        }
//
//        Box(Modifier.height(4.dp))
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Label(
//                text = "Height",
//                modifier = Modifier
//                    .width(50.dp)
//            )
//
//            TextField(
//                value = height?.toString() ?: "",
//                onValueChange = {
//                    save {
//                        if (it.isBlank()) {
//                            height = null
//                        } else if (it.toIntOrNull() != null) {
//                            height = it.toIntOrNull()
//                        }
//                    }
//                },
//            )
//        }
//
//        Box(Modifier.height(8.dp))
//
//        TriStateCheckbox(
//            state = when (resizable) {
//                false -> ToggleableState.Off
//                true -> ToggleableState.On
//            },
//            onClick = {
//                save {
//                    resizable = !resizable
//                }
//            },
//        ) {
//            Label("Resizable")
//        }


//}
}