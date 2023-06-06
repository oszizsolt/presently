package components.list.listitem

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import common.preview.PreviewContainer
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.style.LocalAreaColors
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.theme.Theme
import io.kanro.compose.jetbrains.expui.window.JBWindow

enum class SelectedState {
    NotSelected,
    PrimarySelected,
    SecondarySelected,
    TernarySelected,
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    title: String,
    subtitle: String?
) {
    ListItem(
        selectedState = selectedState,
        onClick = onClick,
        modifier = modifier,
    ) {
        DefaultListItemBody(
            title = title,
            subtitle = subtitle,
        )
    }
}


@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    // TODO:
    // hover state
    // add padding
    content()

}

@Preview
@Composable
fun ListItem_Prev() {
    PreviewContainer {
        ListItem() {
            Label("aaa")
        }
    }

}