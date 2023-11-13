package components.panel

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import io.kanro.compose.jetbrains.expui.control.*


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    panelList: List<PanelItemData>,
    mainPanelContent: @Composable () -> Unit
) {
    var leftPanelSelectedItem: Int? by remember { mutableStateOf(null) }
    val position = remember { mutableStateOf(Offset(0f, 0f)) }
    val dragExitOnLeft = remember { mutableStateOf(false) }
    val dragExitOnRight = remember { mutableStateOf(false) }

    Row(modifier = modifier
        .onPointerEvent(PointerEventType.Move) {
            position.value = it.changes.first().position
        }
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
//                        resource = Icons.Default.AccountBox.toString(),
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
        val isDragEnabled = remember { mutableStateOf(true) }

        val minHeight = with(LocalDensity.current) { 40.dp.toPx() }
        val maxHeight = with(LocalDensity.current) { 300.dp.toPx() }


        // 2. Left side content panel
        if (leftPanelSelectedItem != null) {
            val panelColor by remember { mutableStateOf(Color.Gray) }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(
                        with(LocalDensity.current) {
                            leftPanelWidth
                                .coerceAtLeast(minHeight)
                                .coerceAtMost(maxHeight)
                                .toDp()
                        })

                    .background(color = panelColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val bounds = remember { mutableStateOf(Rect(Offset(0f, 0f), Offset(0f, 0f))) }
                    val minWidth = with(LocalDensity.current) { 40.dp.toPx() }
                    val maxWidth = with(LocalDensity.current) { 300.dp.toPx() }
                    val startPosition = remember { mutableStateOf(Offset(0f, 0f)) }
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
                            .onDrag {
                                leftPanelWidth += it.x
                            }

                        ) {
                            Popup(offset = IntOffset(40, 0)) {
                                // Draw a rectangle shape with rounded corners inside the popup
                                Box(
                                    modifier = Modifier
                                        .background(Color.DarkGray)
                                        .border(1.dp, Color.LightGray)
                                        .padding(7.dp)
                                ) {

                                    Text(
                                        text = "Bounds left: ${bounds.value.left}\n" +
                                                "Bounds right: ${bounds.value.right}\n" +
                                                "Start Pos: ${startPosition.value.x}\n" +
                                                "leftPanelW: $leftPanelWidth\n" +
                                                "isDragOn: ${isDragEnabled.value}\n" +
                                                "Pointer X: ${position.value.x}",
                                        color = Color.Green
                                    )
                                }
                            }
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
}