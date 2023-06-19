package components.panel

import androidx.compose.runtime.Composable


data class PanelItemData(
    var iconResource: String,
    var panelName: String,
    var content: @Composable () -> Unit
)


