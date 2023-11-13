package components.panel.sidepanel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.panel.PanelItem
import layouts.theme.borderColor
import org.jetbrains.skia.paragraph.Direction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.SidePanel(
    panels: List<PanelItem>,
    direction: Direction
) {

    var selectedItem: Int? by remember { mutableStateOf(null) }

    when (direction) {
        Direction.LTR -> {
            SidePanelToolbox(
                panels = panels,
                selectedItem = selectedItem,
                onSelectedItemChange = {
                    selectedItem = it
                }
            )


            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.borderColor)
            )


            SidePanelContent(
                panels = panels,
                selectedItem = selectedItem,
                direction = direction,
            )
        }

        Direction.RTL -> {
            SidePanelContent(
                panels = panels,
                selectedItem = selectedItem,
                direction = direction,
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.borderColor)
            )

            SidePanelToolbox(
                panels = panels,
                selectedItem = selectedItem,
                onSelectedItemChange = {
                    selectedItem = it
                }
            )
        }
    }


}