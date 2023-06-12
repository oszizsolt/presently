package components.panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.ToolBarActionButton


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    mainPanelContent:  @Composable () -> Unit
){
    var leftPanelSelectedItem: Int? by remember { mutableStateOf(null) }

    Row(modifier = modifier) {
        // 1. Left side panel (narrow)
        Column(modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.DarkGray)
        ) {
            PanelItem(modifier = Modifier
                .vertical()
                .rotate(-90f),
                leftPanelSelectedItem == 0,
                {
                    leftPanelSelectedItem = if (leftPanelSelectedItem == 0) { null } else { 0 }
                }
            ){
                Icon(modifier = Modifier
                    .rotate(90f),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "",
                    tint = Color.White

                )
                Text(
                    text = "Panel 1",
                    color = Color.White
                )
            }
            PanelItem(modifier = Modifier
                .vertical()
                .rotate(-90f),
                leftPanelSelectedItem == 1,
                {
                    leftPanelSelectedItem = if (leftPanelSelectedItem == 1) { null } else { 1 }
                }
            ){
                Icon(modifier = Modifier
                    .rotate(90f),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "",
                    tint = Color.White
                )
                Text(
                    text = "Panel 2",
                    color = Color.White
                )
            }
            PanelItem(modifier = Modifier
                .vertical()
                .rotate(-90f),
                leftPanelSelectedItem == 2,
                {
                    leftPanelSelectedItem = if (leftPanelSelectedItem == 2) { null } else { 2 }
                }
            ){
                Icon(modifier = Modifier
                    .rotate(90f),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "",
                    tint = Color.White

                )
                Text(
                    text = "Panel 3",
                    color = Color.White
                )
            }

        }

        // 2. Left side content panel
        if (leftPanelSelectedItem != null) {
            var panelColor by remember { mutableStateOf(Color.Gray) }
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(150.dp)
                .background(color = panelColor)
            ) {
                when (leftPanelSelectedItem) {
                    0 -> {
                        //TODO: Call the Composable function of the corresponding panel from here
                        Label(modifier = Modifier
                            .background(color = Color.DarkGray),
                            text = "Panel 1")
                        panelColor = Color.Blue
                    }
                    1 -> {
                        //TODO: Call the Composable function of the corresponding panel from here
                        Label(modifier = Modifier
                            .background(color = Color.DarkGray),
                            text = "Panel 2")
                        panelColor = Color.Cyan
                    }
                    2 -> {
                        //TODO: Call the Composable function of the corresponding panel from here
                        Label(modifier = Modifier
                            .background(color = Color.DarkGray),
                            text = "Panel 3")
                        panelColor = Color.Yellow
                    }
                    null -> {

                    }
                }
            }
        }

        // 3. Main panel
        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1F)
        ) {
            mainPanelContent()
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }