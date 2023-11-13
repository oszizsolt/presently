package components.panel.sidepanel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.paragraph.Direction


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.SidePanelContentDragArea(
    panelWidth: Float,
    setPanelWidth: (Float) -> Unit,
    minWidth: Float,
    maxWidth: Float,
    direction: Direction,
) {
    Box(
        modifier = Modifier
            .align(
                when (direction) {
                    Direction.LTR -> Alignment.TopEnd
                    Direction.RTL -> Alignment.TopStart
                }
            )
            .width(16.dp)
            .fillMaxHeight()
            .pointerHoverIcon(PointerIcon(org.jetbrains.skiko.Cursor(java.awt.Cursor.W_RESIZE_CURSOR)))
            .onDrag(
                enabled = true,
                onDragEnd = {
                    setPanelWidth(
                        panelWidth
                            .coerceAtLeast(minWidth)
                            .coerceAtMost(maxWidth)
                    )
                },
                onDrag = {
                    when (direction) {
                        Direction.LTR -> setPanelWidth(
                            panelWidth + it.x
                        )
                        Direction.RTL -> setPanelWidth(
                            panelWidth - it.x
                        )
                    }

                }
            )

    )
}