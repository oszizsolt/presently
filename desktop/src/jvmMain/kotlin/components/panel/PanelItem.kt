package components.panel

import androidx.compose.runtime.Composable


data class PanelItem(
    var iconResource: String,
    var panelName: String,
    var content: @Composable () -> Unit
)


