package components.panel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import common.preview.PreviewContainer
import components.list.listitem.SelectedState
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.ToolBarActionButton
import io.kanro.compose.jetbrains.expui.control.onHover


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PanelItem(
    modifier: Modifier = Modifier,
    selectedState: Boolean = false,
    panelName: String = "",
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var hover by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .onHover {
            hover = it
        }
        .onClick {
            onClick()
        }
        .padding(5.dp)
    ) {
        Box(modifier = modifier
            .clip(RoundedCornerShape(25))
            .background(
                when{
                    selectedState -> Color(0,100,255)
                    hover -> Color.Gray
                    else -> Color.Transparent
                }
            )
        ) {
            content()
        }
        if (hover) {
            Popup(offset = IntOffset(40,0)) {
                // Draw a rectangle shape with rounded corners inside the popup
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .border(1.dp, Color.LightGray)
                        .padding(7.dp)
                ) {

                    Text(
                        text = panelName,
                        color = Color.LightGray
                    )
                }
            }
        }
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