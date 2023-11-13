package components.panel.sidepanel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import components.panel.PanelItemIcon
import components.panel.PanelItem
import layouts.theme.borderColor
import layouts.theme.iconColor

@Composable
fun SidePanelToolbox(
    panels: List<PanelItem>,
    selectedItem: Int?,
    onSelectedItemChange: (Int?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        panels.forEachIndexed { index, panelItemData ->
            PanelItemIcon(
                modifier = Modifier,
                selectedState = selectedItem == index,
                panelName = panelItemData.panelName,
                onClick = {
                    onSelectedItemChange(
                        if (selectedItem == index) {
                            null
                        } else {
                            index
                        }
                    )
                }
            ) {
                Image(
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .padding(5.dp),
                    painter = painterResource(panelItemData.iconResource),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.iconColor)
                )
            }
        }

    }
}