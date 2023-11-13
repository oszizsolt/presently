package screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.SegmentedButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
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
                Label("config selector with start button")
            }
        }
    ) {
        // TODO memoize

        when (selectedPart) {
            MainWindowParts.Song -> SongEditorScreen()
            MainWindowParts.List -> SongListEditorScreen()
            MainWindowParts.Config -> ConfigEditorScreen()
            else -> Label(selectedPart.name)
        }
    }
}