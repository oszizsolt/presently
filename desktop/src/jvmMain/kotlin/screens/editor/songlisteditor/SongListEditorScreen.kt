package screens.editor.songlisteditor

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton

@Composable
fun SongListEditorScreen() {
    Column {
        PrimaryButton(onClick = {}) {
            Label("preview")
        }

        PrimaryButton(onClick = {}) {
            Label("play")
        }
    }

}