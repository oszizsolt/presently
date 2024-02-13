package components.presentation.output.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.*
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import java.awt.GraphicsEnvironment

@Composable
fun NDIOutputEditor(
    config: NDIPresentationOutputConfig,
    onConfigChange: (NDIPresentationOutputConfig) -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        var name by remember { mutableStateOf<String>(config.name) }
        var width by remember { mutableStateOf<Int?>(config.width) }
        var height by remember { mutableStateOf<Int?>(config.height) }

        val save: (() -> Unit) -> Unit = { callback ->
            callback()

            onConfigChange(
                NDIPresentationOutputConfig(
                    name = name,
                    width = width ?: 1920,
                    height = height ?: 1080,
                )
            )
        }


        Box(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(
                text = "Name",
                modifier = Modifier
                    .width(50.dp)
            )

            TextField(
                value = name,
                onValueChange = {
                    save {
                        name = it
                    }
                },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(
                text = "Width",
                modifier = Modifier
                    .width(50.dp)
            )

            TextField(
                value = width.toString(),
                onValueChange = {
                    save {
                        if (it.isBlank()) {
                            width = null
                        } else if (it.toIntOrNull() != null) {
                            width = it.toIntOrNull()
                        }
                    }
                },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(
                text = "Height",
                modifier = Modifier
                    .width(50.dp)
            )

            TextField(
                value = height?.toString() ?: "",
                onValueChange = {
                    save {
                        if (it.isBlank()) {
                            height = null
                        } else if (it.toIntOrNull() != null) {
                            height = it.toIntOrNull()
                        }
                    }
                },
            )
        }
    }
}