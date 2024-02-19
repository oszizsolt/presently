package components.presentation.editor.keyvalueeditor


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
