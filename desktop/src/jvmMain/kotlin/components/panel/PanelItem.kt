package components.panel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.preview.PreviewContainer
import components.list.listitem.SelectedState
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.onHover


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PanelItem(
    modifier: Modifier = Modifier,
    selectedState: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var hover by remember { mutableStateOf(false) }
    Row(modifier = modifier
        .onHover {
            hover = it
        }
        .background(
            when{
                selectedState -> Color.Blue
                hover -> Color.Gray
                else -> Color.Transparent
            }
        )
        .onClick{
            onClick()
        }
        .padding(5.dp) // The whole view is rotated by -90° -> Horizontal is vertical and vice versa
    ){
        content()
    }

}

@Preview
@Composable
fun ListItem_Prev() {
    PreviewContainer {
        PanelItem {
            Label("Alma")
        }
    }
}