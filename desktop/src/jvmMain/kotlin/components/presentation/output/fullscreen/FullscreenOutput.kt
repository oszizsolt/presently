package components.presentation.output.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import java.awt.GraphicsEnvironment

@Composable
fun FullscreenOutput(
    config: FullscreenPresentationOutputConfig,
    content: @Composable () -> Unit
) {
    Window(
        undecorated = true,
        focusable = false,
        resizable = false,
        onCloseRequest = {},
    ) {

        DisposableEffect(window, config.displayId) {

            val device = if (config.displayId != null) {
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .screenDevices.find { it.iDstring == config.displayId }
            } else {
                GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice
            }

            device?.fullScreenWindow = window

            onDispose {
                device?.fullScreenWindow = null
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}