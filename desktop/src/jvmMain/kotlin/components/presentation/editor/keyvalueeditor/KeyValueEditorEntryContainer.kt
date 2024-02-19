package components.presentation.editor.keyvalueeditor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.Label

@Composable
fun KeyValueEditorEntryContainer(
    title: String,
    error: String?,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Label(
                text = title,
                modifier = Modifier
                    .width(50.dp)
                    .padding(end = 2.dp)
            )

            content()
        }


        error?.let {
            Label(
                text = it,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
    }
}