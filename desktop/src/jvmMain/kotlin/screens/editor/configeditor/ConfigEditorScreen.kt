package screens.editor.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onClick
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.panel.PanelItem
import components.panel.PanelLayout
import components.presentation.editor.PresentationEditorEntry
import components.presentation.output.PresentationOutputEditorFactory
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.presently.service.config.Config
import io.presently.service.engine.presentationoutput.OutputConfig
import kotlinx.coroutines.launch
import screens.main.LocalConfigService
import screens.main.LocalCurrentConfig

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConfigEditorScreen() {

    val currentConfig = LocalCurrentConfig.current

    val configService = LocalConfigService.current
    val configs by configService.get().collectAsState(initial = emptyList())

    // TODO:
    // - selectedConfig         <- done?
    // - config editors for every config type + factory         <- done
    // - create (maybe: paralell) task: new and safer editor ui with save button and error handling (popup?)
    // - config create button
    // - show empty screen, if the selected output does not belong to the selected config (to avoid confusion) ((or add tab layout))
    // - move config selection to the left side
    // - add current config output list to right side
    // - add current output and slide settings to the center
    // - rework service architecture (add flows to the service, so it can be refreshed)
    // - if the current config changes, add a reload icon, to reload teh current config without downtime
    // - main viewmodel?? (ot at least split it up)
    // - test fullscreen
    // - fix focusing window bug
    // - add logging later

    var selectedConfigName by remember { mutableStateOf<String?>(null) }

    PanelLayout(
        leftPanels = listOf(
            PanelItem(
                iconResource = "icons/bars-solid.svg",
                panelName = "Configurations",
            ) {
                Column {
                    configs.forEach { config ->
                        key(config.name) {
                            Label(
                                config.name,
                                modifier = Modifier
                                    .height(32.dp)
                                    .fillMaxWidth()
                                    .background(
                                        if (config.name == selectedConfigName && config.name == currentConfig?.name) {
                                            Color.Cyan
                                        } else if (config.name == selectedConfigName) {
                                            Color.Blue
                                        } else if (config.name == currentConfig?.name) {
                                            Color.Green
                                        } else {
                                            Color.Transparent
                                        }
                                    )
                                    .onClick {
                                        selectedConfigName = config.name
                                    }
                            )
                        }
                    }
                }
            }
        ),
        rightPanels = listOf()
    ) {
        selectedConfigName?.let { selectedConfigName ->
            Column {
                Label(
                    text = selectedConfigName,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 16.dp),
                )

                val selectedConfig = configs.find { it.name == selectedConfigName }

                selectedConfig?.outputs?.forEach { output ->
                    key(output.name) {
                        PresentationEditorEntry(
                            config = output,
                            onSave = { newOutput ->
                                configService.put(
                                    config = selectedConfig.copy(
                                        outputs = selectedConfig.outputs.map {
                                            if (it == output) {
                                                newOutput
                                            } else {
                                                it
                                            }
                                        }
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }


    }
}