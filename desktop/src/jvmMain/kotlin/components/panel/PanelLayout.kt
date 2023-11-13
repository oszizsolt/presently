package components.panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import components.panel.sidepanel.SidePanel
import org.jetbrains.skia.paragraph.Direction


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PanelLayout(
    modifier: Modifier = Modifier,
    leftPanels: List<PanelItem> = emptyList(),
    rightPanels: List<PanelItem> = emptyList(),
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
