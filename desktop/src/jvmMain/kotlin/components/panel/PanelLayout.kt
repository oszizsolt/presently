package components.panel

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.*
import io.kanro.compose.jetbrains.expui.style.AreaColors
import io.kanro.compose.jetbrains.expui.style.LocalErrorAreaColors
import io.kanro.compose.jetbrains.expui.style.LocalHoverAreaColors
import java.awt.Image


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    panelList: List<PanelItemData>,
    mainPanelContent: @Composable () -> Unit
) {
    var leftPanelSelectedItem: Int? by remember { mutableStateOf(null) }

    Row(modifier = modifier) {
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
        var isDragEnabled = remember { mutableStateOf(true) }

        // 2. Left side content panel
        if (leftPanelSelectedItem != null) {
            val panelColor by remember { mutableStateOf(Color.Gray) }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(with(LocalDensity.current) { leftPanelWidth.toDp() })
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
//                        when (leftPanelSelectedItem) {
//                            0 -> {
//                                //TODO: Call the Composable function of the corresponding panel from here
//                                Label(
//                                    modifier = Modifier
//                                        .background(color = Color.DarkGray),
//                                    text = "Panel 1"
//                                )
//                                panelColor = Color.Blue
//                            }
//
//                            1 -> {
//                                //TODO: Call the Composable function of the corresponding panel from here
//                                Label(
//                                    modifier = Modifier
//                                        .background(color = Color.DarkGray),
//                                    text = "Panel 2"
//                                )
//                                panelColor = Color.Cyan
//                            }
//
//                            2 -> {
//                                //TODO: Call the Composable function of the corresponding panel from here
//                                Label(
//                                    modifier = Modifier
//                                        .background(color = Color.DarkGray),
//                                    text = "Panel 3"
//                                )
//                                panelColor = Color.Yellow
//                            }
//
//                            null -> {
//
//                            }
//                        }
                    }

                    val minHeight = with(LocalDensity.current) { 40.dp.toPx() }
                    val maxHeight = with(LocalDensity.current) { 300.dp.toPx() }
                    val position = remember { mutableStateOf(Offset(0f,0f)) }
                    val bounds = remember { mutableStateOf(Rect(Offset(0f,0f),Offset(0f,0f))) }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned {
                                bounds.value = it.boundsInRoot()
                            }
                            .align(Alignment.TopEnd)
                            .width(16.dp)
                            .fillMaxHeight()
                            .pointerHoverIcon(PointerIcon(org.jetbrains.skiko.Cursor(java.awt.Cursor.W_RESIZE_CURSOR)))
                            .onPointerEvent(PointerEventType.Move) {
                                position.value = it.changes.first().position
//                                color = Color(position.x.toInt() % 256, position.y.toInt() % 256, 0)
                            }
                            .onDrag(
                                onDragStart = {
                                    println("OnDrag Start")
                                          },
                                onDragEnd = {
                                    println("OnDrag End")
                                },
                                enabled = isDragEnabled.value,
                            ) {
                                println("pointer x: ${position.value.x.toInt()+bounds.value.right}, bounds right: ${bounds.value.right+30}")
                                if ((bounds.value.right+position.value.x.toInt()) < bounds.value.right+30){
                                    leftPanelWidth = (leftPanelWidth + it.x)
                                        .coerceAtLeast(minHeight)
                                        .coerceAtMost(maxHeight)
                                }

                            }
//                            .onPointerEvent(PointerEventType.Enter) { isDragEnabled.value = true }
//                            .onPointerEvent(PointerEventType.Exit) { isDragEnabled.value = false }
//                            .pointerInput(Unit) {
//                                detectDragGestures(
//                                    matcher = PointerMatcher.Primary
//                                ) {
//                                    leftPanelWidth = (leftPanelWidth + it.x)
//                                        .coerceAtMost(maxHeight)
//                                }
//                            }

                    )
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