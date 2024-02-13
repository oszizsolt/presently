package components.presentation.output.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.*
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import java.awt.GraphicsDevice
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