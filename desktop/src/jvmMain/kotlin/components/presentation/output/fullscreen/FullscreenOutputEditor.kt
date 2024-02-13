package components.presentation.output.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.*
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import java.awt.GraphicsEnvironment

@Composable
fun FullscreenOutputEditor(
    config: FullscreenPresentationOutputConfig,
    onConfigChange: (FullscreenPresentationOutputConfig) -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        var displayId by remember { mutableStateOf<String?>(config.displayId) }

        val save: (() -> Unit) -> Unit = { callback ->
            callback()

            onConfigChange(
                FullscreenPresentationOutputConfig(
                    displayId = displayId,
                )
            )
        }


        Box(Modifier.height(8.dp))

        val filteredDisplays = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .screenDevices
            .filter { screen ->
                screen.isFullScreenSupported
            }

        val displays = filteredDisplays
            .associate { screen ->
                screen.iDstring to screen.displayMode
            }

        val displayIds = filteredDisplays.map { screen ->
            screen.iDstring
        }

        ComboBox(
            items = displayIds,
            value = displayId,
            onValueChange = { id ->
                save {
                    displayId = id
                }
            },
            modifier = Modifier.width(250.dp),
            menuModifier = Modifier.width(250.dp),
            valueRender = { id ->
                Label(text = displays[id]?.toString() ?: id ?: "-")
            }
        )

    }
}