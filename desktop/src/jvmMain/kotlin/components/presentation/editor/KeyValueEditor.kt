package components.presentation.editor

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kanro.compose.jetbrains.expui.control.ComboBox
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.kanro.compose.jetbrains.expui.control.TextField
import kotlinx.coroutines.launch


sealed interface KeyValueEditorValueType {
    fun errorOf(value: String?): String?
}

data class StringKeyValueEditorValueType(
    val canBeBlank: Boolean,
) : KeyValueEditorValueType {
    override fun errorOf(value: String?): String? {
        if (!canBeBlank && value.isNullOrBlank()) {
            return "Value cannot be blank!"
        }

        return null
    }
}

data class IntKeyValueEditorValueType(
    val canBeNull: Boolean,
    val min: Int?,
    val max: Int?,
) : KeyValueEditorValueType {
    override fun errorOf(value: String?): String? {
        if (!canBeNull && value == null) {
            return "Value cannot be empty!"
        }

        if (value != null && value.toIntOrNull() == null) {
            return "Value must be an integer!"
        }

        if (max != null && value?.toIntOrNull() != null && value.toInt() > max) {
            return "Value must not be bigger than $max!"
        }

        if (min != null && value?.toIntOrNull() != null && value.toInt() < min) {
            return "Value must not be smaller than $min!"
        }

        return null
    }
}

data class DoubleKeyValueEditorValueType(
    val canBeNull: Boolean,
    val min: Double?,
    val max: Double?,
) : KeyValueEditorValueType {
    override fun errorOf(value: String?): String? {
        if (!canBeNull && value == null) {
            return "Value cannot be empty!"
        }

        if (value != null && value.toDoubleOrNull() == null) {
            return "Value must be a number!"
        }

        if (max != null && value?.toDoubleOrNull() != null && value.toDouble() > max) {
            return "Value must not be bigger than $max!"
        }

        if (min != null && value?.toDoubleOrNull() != null && value.toDouble() < min) {
            return "Value must not be smaller than $min!"
        }

        return null
    }
}

data class EnumKeyValueEditorValueType(
    val values: Map<String?, String>,
) : KeyValueEditorValueType {
    override fun errorOf(value: String?): String? {
        if (!values.containsKey(value)) {
            return "Invalid value!"
        }

        return null
    }
}


@Composable
fun KeyValueEditorEntryContainer(
    title: String,
    error: String?,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row {
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

@Composable
fun KeyValueEditor(
    config: Map<String, KeyValueEditorValueType>,
    initialValues: Map<String, String?>,
    onSave: suspend (Map<String, String?>) -> Unit
) {
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