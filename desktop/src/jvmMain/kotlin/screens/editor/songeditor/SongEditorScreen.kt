package screens.editor.songeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton

@Composable
fun SongEditorScreen() {
    Column {
        PrimaryButton(onClick = {}) {
            Label("preview")
        }

        PrimaryButton(onClick = {}) {
            Label("play")
        }
    }
}