package screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import components.presentation.EngineOutputFactory
import io.kanro.compose.jetbrains.expui.control.*
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.config.Config
import io.presently.service.engine.PresentationEngine
import io.presently.service.engine.PresentationEngineImplementation
import io.presently.service.engine.PresentationMode
import screens.editor.bibleeditor.BibleEditorScreen
import screens.editor.configeditor.ConfigEditorScreen
import screens.editor.songeditor.SongEditorScreen
import screens.editor.songlisteditor.SongListEditorScreen

enum class MainWindowParts {
    Song,
    List,
    Bible,
    Config,
}

@Composable
fun ApplicationScope.MainWindow() {
    var selectedPart by remember { mutableStateOf(MainWindowParts.Song) }
    var presentationEngine by remember { mutableStateOf<PresentationEngine?>(null) }
    var currentConfig by remember { mutableStateOf<Config?>(null) }

    JBWindow(
        theme = DarkTheme,
        title = "",
        onCloseRequest = ::exitApplication,
        mainToolBar = {
            Row(
                Modifier
                    .padding(start = 12.dp)
                    .mainToolBarItem(Alignment.CenterHorizontally)
            ) {
                val selectedIndex = MainWindowParts.values().indexOf(selectedPart)

                SegmentedButton(MainWindowParts.values().size, selectedIndex, {
                    val newSelectedPart = MainWindowParts.values()[it]

                    selectedPart = newSelectedPart
                }) {
                    val selectedPart = MainWindowParts.values()[it]

                    when (selectedPart) {
                        MainWindowParts.Song -> Label("Songs")
                        MainWindowParts.List -> Label("Lists")
                        MainWindowParts.Bible -> Label("Bible")
                        MainWindowParts.Config -> Label("Config")
                    }
                }
            }

            Row(
                Modifier
                    .padding(end = 12.dp)
                    .mainToolBarItem(Alignment.End)
            ) {

                val configService = LocalConfigService.current
                var isMenuExpanded by remember { mutableStateOf(false) }
                val configs by configService.get().collectAsState(initial = emptyList())

                DropdownLink(
                    text = currentConfig?.name ?: "No configuration",
                    onClick = { isMenuExpanded = true },
                    enabled = presentationEngine == null,
                )
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = {
                        isMenuExpanded = false
                    },
                ) {
                    configs.forEach { config ->
                        DropdownMenuItem({
                            currentConfig = config
                            isMenuExpanded = false
                        }) {
                            Label(config.name)
                        }
                    }

                    DropdownMenuItem({
                        currentConfig = null
                        isMenuExpanded = false
                    }) {
                        Label("-")
                    }
                }

                ActionButton(
                    onClick = {
                        presentationEngine = if (presentationEngine == null) {
                            PresentationEngineImplementation(
                                outputs = currentConfig?.outputs ?: emptyList(),
                            )
                        } else {
                            null
                        }
                    },
                    enabled = currentConfig != null,
                ) {
                    Icon(
                        if (presentationEngine == null) {
                            "icons/run_dark.svg"
                        } else {
                            "icons/stop_dark.svg"
                        }
                    )
                }
            }
        }
    ) {
        // TODO memoize (or use viewModels instead)

        CompositionLocalProvider(LocalCurrentConfig provides currentConfig) {
            presentationEngine?.let { presentationEngine ->
                val currentSlide by presentationEngine.current.collectAsState(initial = null)
                val previewSlide by presentationEngine.preview.collectAsState(initial = null)
                val presentationMode by presentationEngine.presentationMode.collectAsState(initial = PresentationMode.Hidden)

                presentationEngine.outputs.forEach { output ->
                    key(output.name) {
                        EngineOutputFactory(
                            slide = currentSlide,
                            nextSlide = previewSlide,
                            presentationMode = presentationMode,
                            outputConfig = output.outputConfig,
                            slideConfig = output.slideConfig,
                        )
                    }
                }
            }

            CompositionLocalProvider(LocalEngine provides presentationEngine) {
                when (selectedPart) {
                    MainWindowParts.Song -> SongEditorScreen()
                    MainWindowParts.List -> SongListEditorScreen()
                    MainWindowParts.Config -> ConfigEditorScreen()
                    MainWindowParts.Bible -> BibleEditorScreen()
                }
            }
        }
    }
}