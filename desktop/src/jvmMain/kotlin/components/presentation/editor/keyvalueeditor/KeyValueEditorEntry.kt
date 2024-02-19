package components.presentation.editor.keyvalueeditor

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.ComboBox
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.TextField

@Composable
fun KeyValueEditorEntry(
    type: KeyValueEditorValueType,
    value: String?,
    onChange: (String?) -> Unit,
) {
    when (type) {
        is StringKeyValueEditorValueType -> {
            TextField(
                value = value ?: "",
                onValueChange = {
                    onChange(it)
                },
            )
        }

        is DoubleKeyValueEditorValueType -> {
            TextField(
                value = value ?: "",
                onValueChange = {
                    onChange(it)
                },
            )
        }

        is IntKeyValueEditorValueType -> {
            TextField(
                value = value ?: "",
                onValueChange = {
                    onChange(it)
                },
            )
        }

        is EnumKeyValueEditorValueType -> {
            ComboBox(
                items = type.values.keys.toList(),
                value = value,
                onValueChange = {
                    onChange(it)
                },
                valueRender = { key ->
                    Label(type.values[key] ?: key ?: "-")
                },
                modifier = Modifier.width(250.dp),
                menuModifier = Modifier.width(250.dp),
            )
        }
    }
}