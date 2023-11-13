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


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    panelList: List<PanelItemData>,
    mainPanelContent: @Composable () -> Unit
) {
    var leftPanelSelectedItem: Int? by remember { mutableStateOf(null) }

    Row(
        modifier = modifier,
    ) {
        // 1. Left side panel (narrow)
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            panelList.forEachIndexed { index, panelItemData ->
                PanelItem(
                    modifier = Modifier,
                    selectedState = leftPanelSelectedItem == index,
                    panelName = panelItemData.panelName,
                    {
                        leftPanelSelectedItem = if (leftPanelSelectedItem == index) {
                            null
                        } else {
                            index
                        }
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                            .padding(5.dp),
                        painter = painterResource(panelItemData.iconResource),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }

        }
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color.Black)
        )

        val defaultSize = with(LocalDensity.current) { 150.dp.toPx() }
        var leftPanelWidth by remember { mutableStateOf(defaultSize) }

        val minWidth = with(LocalDensity.current) { 40.dp.toPx() }
        val maxWidth = with(LocalDensity.current) { 300.dp.toPx() }


        // 2. Left side content panel
        if (leftPanelSelectedItem != null) {
            val panelColor by remember { mutableStateOf(Color.Gray) }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(
                        with(LocalDensity.current) {
                            leftPanelWidth
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
                        if (leftPanelSelectedItem != null) {
                            panelList[leftPanelSelectedItem!!].content()
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(16.dp)
                            .fillMaxHeight()
                            .pointerHoverIcon(PointerIcon(org.jetbrains.skiko.Cursor(java.awt.Cursor.W_RESIZE_CURSOR)))
                            .onDrag(
                                enabled = true,
                                onDragEnd = {
                                    leftPanelWidth = leftPanelWidth
                                        .coerceAtLeast(minWidth)
                                        .coerceAtMost(maxWidth)
                                },
                                onDrag = {
                                    leftPanelWidth += it.x
                                }
                            )

                    ) {


                    }
                }
            }
        }

        // 3. Main panel
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
        ) {
            mainPanelContent()
        }
    }
}
