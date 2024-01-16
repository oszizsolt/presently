package screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import components.presentation.EngineOutputFactory
import components.presentation.output.WindowOutput
import components.presentation.slide.bible.BiblePresentation
import components.presentation.slide.song.StageViewSongPresentation
import components.presentation.slide.song.SongPresentation
import io.kanro.compose.jetbrains.expui.control.ActionButton
import io.kanro.compose.jetbrains.expui.control.Icon
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.SegmentedButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.bible.BibleSlide
import io.presently.service.engine.PresentationEngine
import io.presently.service.engine.PresentationEngineImplementation
import io.presently.service.engine.PresentationMode
import io.presently.service.engine.presentationoutput.OutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig
import io.presently.service.song.SongSlide
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

                Label("selected config")

                ActionButton(
                    onClick = {
                        presentationEngine = if (presentationEngine == null) {
                            PresentationEngineImplementation(
                                // TODO DB arch
                                outputs = listOf(
                                    OutputConfig(
                                        name = "Test Stage View",
                                        outputConfig = WindowPresentationOutputConfig(
                                            resolution = WindowPresentationOutputConfig.FixedResolution(
                                                width = 640,
                                                height = 480,
                                                resizable = false,
                                            ),
                                        ),
                                        slideConfig = StageViewSongPresentationSlideConfig(
                                            font = null,
                                            previewFont = null,
                                            fontSize = 18,
                                            previewFontSize = 16,
                                            fontColor = 0xffffffffL,
                                            previewFontColor = 0xffffffffL,
                                            backgroundColor = 0xff000000L,
                                        ),
                                    ),
                                    OutputConfig(
                                        name = "Test Bible",
                                        outputConfig = WindowPresentationOutputConfig(
                                            resolution = WindowPresentationOutputConfig.FixedResolution(
                                                width = 640,
                                                height = 480,
                                                resizable = false,
                                            ),
                                        ),
                                        slideConfig = BiblePresentationSlideConfig(
                                            font = null,
                                            fontSize = 18,
                                            fontColor = 0xffffffffL,
                                            backgroundColor = 0xff000000L,
                                            verseFontColor = 0xffffffffL,
                                            verseFontSize = 14,
                                            verseFont = null,
                                        ),
                                    ),
                                )
                            )
                        } else {
                            null
                        }
                    },
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