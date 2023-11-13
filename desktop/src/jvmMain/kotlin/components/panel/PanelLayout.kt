package components.panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import components.panel.sidepanel.SidePanel
import org.jetbrains.skia.paragraph.Direction


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    leftPanels: List<PanelItemData> = emptyList(),
    rightPanels: List<PanelItemData> = emptyList(),
    mainPanelContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        if (leftPanels.isNotEmpty()) {
            SidePanel(
                panels = leftPanels,
                direction = Direction.LTR,
            )
        }


        // 3. Main panel
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
        ) {
            mainPanelContent()
        }

        if (rightPanels.isNotEmpty()) {
            SidePanel(
                panels = rightPanels,
                direction = Direction.RTL,
            )
        }


    }
}
