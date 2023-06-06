package components.list.listitem

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.preview.PreviewContainer
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.onHover
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var hover by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .onHover {
            hover = it
        }
        .background(
            when{
                selectedState == SelectedState.PrimarySelected -> Color.Blue
                selectedState == SelectedState.SecondarySelected -> Color.Blue.copy(red = 0.5f, green = 0.5f)
                selectedState == SelectedState.TernarySelected -> Color.Gray
                hover -> Color.DarkGray
                else -> Color.Transparent
            }
        )
        .onClick{
            onClick()
        }
        .padding(vertical = 8.dp, horizontal = 16.dp)
    ){
        content()
    }

}

@Preview
@Composable
fun ListItem_Prev() {
    PreviewContainer {
        ListItem() {
            Label("Alma")
        }
    }

}