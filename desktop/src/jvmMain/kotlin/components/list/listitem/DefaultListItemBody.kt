package components.list.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import io.kanro.compose.jetbrains.expui.control.Label

@Composable
internal fun DefaultListItemBody(
    title: String,
    subtitle: String?
) {
    Column {
        Label(title)

        subtitle?.let { subtitle ->
            Label(subtitle)
        }
    }
}
