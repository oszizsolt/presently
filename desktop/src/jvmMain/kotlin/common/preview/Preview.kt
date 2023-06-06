package common.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.list.listitem.ListItem
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.style.LocalAreaColors
import io.kanro.compose.jetbrains.expui.theme.DarkTheme


@Composable
fun PreviewContainer(
    content: @Composable () -> Unit,
) {
    DarkTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(LocalAreaColors.current.startBackground)
        ) {
            content()
        }
    }
}