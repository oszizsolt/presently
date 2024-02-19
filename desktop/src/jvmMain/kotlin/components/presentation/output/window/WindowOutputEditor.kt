package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.EnumKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.IntKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig

@Composable
fun WindowOutputEditor(
    config: WindowPresentationOutputConfig,
    onConfigChange: suspend (WindowPresentationOutputConfig) -> Unit
) {
    KeyValueEditor(
        title = "${config.title} Settings",
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