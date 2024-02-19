package components.presentation.editor.keyvalueeditor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import kotlinx.coroutines.launch


@Composable
fun KeyValueEditor(
    title: String,
    config: Map<String, KeyValueEditorValueType>,
    initialValues: Map<String, String?>,
    onSave: suspend (Map<String, String?>) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(vertical = 24.dp)
    ) {
        Label(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )

        Box(Modifier.height(12.dp))

        val scope = rememberCoroutineScope()

        var customError by remember { mutableStateOf<String?>(null) }

        var values by remember { mutableStateOf(initialValues) }

        val errors = config.mapValues { (key, type) ->
            val value = values[key]

            type.errorOf(value)
        }

        Column(
            Modifier.fillMaxSize(),
        ) {
            config.forEach { (key, type) ->
                val value = values[key]

                KeyValueEditorEntryContainer(
                    title = key,
                    error = errors[key],
                ) {
                    KeyValueEditorEntry(
                        type = type,
                        value = value,
                        onChange = {
                            values = values + (key to it)
                        }
                    )
                }
            }

            customError?.let {
                Label(
                    text = it,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier
                        .width(50.dp)
                )
            }

            Box(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                PrimaryButton(
                    enabled = customError == null && errors.all { (key, value) -> value == null },
                    onClick = {
                        scope.launch {
                            try {
                                onSave(values)
                            } catch (e: Exception) {
                                customError = e.message
                                e.printStackTrace()
                                // TODO
                            }
                        }

                    }
                ) {
                    Label("Save")
                }
            }
        }
    }
}