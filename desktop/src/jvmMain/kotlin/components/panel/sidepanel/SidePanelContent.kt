package components.panel.sidepanel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import components.panel.PanelItem
import org.jetbrains.skia.paragraph.Direction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SidePanelContent(
    panels: List<PanelItem>,
    selectedItem: Int?,
    direction: Direction,
) {
    val minWidth = with(LocalDensity.current) { 40.dp.toPx() }
    val maxWidth = with(LocalDensity.current) { 300.dp.toPx() }

    val defaultSize = with(LocalDensity.current) { 150.dp.toPx() }
    var panelWidth by remember { mutableStateOf(defaultSize) }

    if (selectedItem != null) {
        val panelColor by remember { mutableStateOf(Color.Gray) }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(
                    with(LocalDensity.current) {
                        panelWidth
                            .coerceAtLeast(minWidth)
                            .coerceAtMost(maxWidth)
                            .toDp()
                    })

                .background(color = panelColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    panels[selectedItem!!].content()
                }

                SidePanelContentDragArea(
                    panelWidth = panelWidth,
                    setPanelWidth = {
                        panelWidth = it
                    },
                    minWidth = minWidth,
                    maxWidth = maxWidth,
                    direction = direction,
                )
            }
        }
    }
}