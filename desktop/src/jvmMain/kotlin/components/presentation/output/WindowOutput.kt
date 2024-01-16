package components.presentation.output

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
        placement = if (config.resolution is WindowPresentationOutputConfig.Fullscreen) {
            WindowPlacement.Fullscreen
        } else {
            WindowPlacement.Floating
        },
        width = if (config.resolution is WindowPresentationOutputConfig.FixedResolution) {
            with(LocalDensity.current) {
                (config.resolution as WindowPresentationOutputConfig.FixedResolution).width.toDp()
            }
        } else {
            Dp.Unspecified
        },
        height = if (config.resolution is WindowPresentationOutputConfig.FixedResolution) {
            with(LocalDensity.current) {
                (config.resolution as WindowPresentationOutputConfig.FixedResolution).height.toDp()
            }
        } else {
            Dp.Unspecified
        },
        isMinimized = false,
        position = WindowPosition.PlatformDefault,
    )



    JBWindow(
        theme = DarkTheme,
        state = windowsState,
        focusable = false,
        resizable = if (config.resolution is WindowPresentationOutputConfig.FixedResolution) {
            (config.resolution as WindowPresentationOutputConfig.FixedResolution).resizable
        } else {
            false
        },
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