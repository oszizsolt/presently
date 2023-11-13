package components.panel.sidepanel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import components.panel.PanelItem
import components.panel.PanelItemData
import org.jetbrains.skia.paragraph.Direction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.SidePanel(
    panels: List<PanelItemData>,
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
                    .background(Color.Black)
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
                    .background(Color.Black)
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