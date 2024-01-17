package screens.editor.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.onClick
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.panel.PanelItem
import components.panel.PanelLayout
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.presently.service.config.Config
import io.presently.service.engine.presentationoutput.OutputConfig
import screens.main.LocalConfigService
import screens.main.LocalCurrentConfig

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConfigEditorScreen() {

    val currentConfig = LocalCurrentConfig.current

    val configService = LocalConfigService.current
    var configs by remember { mutableStateOf(emptyList<Config>()) }

    LaunchedEffect(Unit) {
        configs = configService.get()
    }

    // TODO:
    // - selectedConfig
    // - config editors for every config type + factory
    // - move config selection to the left side
    // - add current config output list to right side
    // - add current output and slide settings to the center
    // - rework service architecture (add flows to the service, so it can be refreshed)
    // - if the current config changes, add a reload icon, to reload teh current config without downtime
    // - main viewmodel?? (ot at least split it up)
    // - test fullscreen
    // - fix focusing window bug
    // - add logging later

    var selectedConfig by remember { mutableStateOf<Config?>(null) }
    var selectedOutput by remember { mutableStateOf<OutputConfig?>(null) }

    PanelLayout(
        leftPanels = listOf(
            PanelItem(
                iconResource = "icons/bars-solid.svg",
                panelName = "Configurations",
            ) {
                Column {
                    configs.forEach { config ->
                        Label(
                            config.name,
                            modifier = Modifier
                                .height(32.dp)
                                .fillMaxWidth()
                                .background(
                                    if (config == selectedConfig && config.name == currentConfig?.name) {
                                        Color.Cyan
                                    } else if (config == selectedConfig) {
                                        Color.Blue
                                    } else if (config.name == currentConfig?.name) {
                                        Color.Green
                                    } else {
                                        Color.Transparent
                                    }
                                )
                                .onClick {
                                    selectedConfig = config
                                }
                        )
                    }
                }
            }
        ),
        rightPanels = listOf(
            PanelItem(
                iconResource = "icons/bars-solid.svg",
                panelName = "Configurations",
            ) {
                Column {
                    selectedConfig?.outputs?.forEach { output ->
                        Label(
                            output.name,
                            modifier = Modifier
                                .height(32.dp)
                                .fillMaxWidth()
                                .background(
                                    if (output == selectedOutput) {
                                        Color.Blue
                                    } else {
                                        Color.Transparent
                                    }
                                )
                                .onClick {
                                    selectedOutput = output
                                }
                        )
                    }
                }
            }
        )
    ) {
        Label(selectedOutput?.name ?: "hamham")
    }
}