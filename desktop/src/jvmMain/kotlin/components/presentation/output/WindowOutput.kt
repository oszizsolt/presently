package components.presentation.output

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow

@Composable
fun WindowOutput(
    backgroundColor: Color = Color.Black,
    content: @Composable () -> Unit
) {
    JBWindow(
        theme = DarkTheme,
        resizable = true,
        onCloseRequest = {},
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            content()
        }
    }
}