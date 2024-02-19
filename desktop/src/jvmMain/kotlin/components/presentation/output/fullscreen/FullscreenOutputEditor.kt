package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.EnumKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import java.awt.GraphicsEnvironment

@Composable
fun FullscreenOutputEditor(
    config: FullscreenPresentationOutputConfig,
    onConfigChange: suspend (FullscreenPresentationOutputConfig) -> Unit
) {

    val filteredDisplays = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .screenDevices
        .filter { screen ->
            screen.isFullScreenSupported
        }

    val displays = filteredDisplays
        .associate { screen ->
            screen.iDstring to screen.displayMode.toString()
        }
        .plus(null to "Always the other display, if possible")


    KeyValueEditor(
        title = "${config.title} Settings",
        config = mapOf(
            "Display" to EnumKeyValueEditorValueType(
                values = displays,
            ),
        ),
        initialValues = mapOf(
            "Display" to config.displayId,
        ),
        onSave = {
            onConfigChange(
                FullscreenPresentationOutputConfig(
                    displayId = it["Display"]!!,
                )
            )
        }
    )

//    Column(
//        modifier = Modifier
//    ) {
//        var displayId by remember(config.displayId) { mutableStateOf<String?>(config.displayId) }
//
//        val save: (() -> Unit) -> Unit = { callback ->
//            callback()
//
//            onConfigChange(
//                FullscreenPresentationOutputConfig(
//                    displayId = displayId,
//                )
//            )
//        }
//
//
//        Box(Modifier.height(8.dp))
//
//        ComboBox(
//            items = displayIds,
//            value = displayId,
//            onValueChange = { id ->
//                save {
//                    displayId = id
//                }
//            },
//            modifier = Modifier.width(250.dp),
//            menuModifier = Modifier.width(250.dp),
//            valueRender = { id ->
//                Label(text = displays[id]?.toString() ?: id ?: "-")
//            }
//        )
//
//    }
}