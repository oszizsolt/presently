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
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig

@Composable
fun WindowOutput(
    config: WindowPresentationOutputConfig,
    content: @Composable () -> Unit
) {

    val windowsState = rememberWindowState(
        placement = WindowPlacement.Floating,
        width = with(LocalDensity.current) {
            config.width.toDp()
        },
        height = with(LocalDensity.current) {
            config.height.toDp()
        },
        isMinimized = false,
        position = WindowPosition.PlatformDefault,
    )


    JBWindow(
        theme = DarkTheme,
        state = windowsState,
        focusable = false,
        resizable = config.resizable,
        onCloseRequest = {},
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}