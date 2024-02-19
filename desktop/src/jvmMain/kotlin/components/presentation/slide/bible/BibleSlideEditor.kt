package components.presentation.output.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.presentation.editor.IntKeyValueEditorValueType
import components.presentation.editor.KeyValueEditor
import components.presentation.editor.KeyValueEditorValueType
import components.presentation.editor.StringKeyValueEditorValueType
import io.kanro.compose.jetbrains.expui.control.*
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import java.awt.GraphicsEnvironment

@Composable
fun BibleSlideEditor(
    config: BiblePresentationSlideConfig,
    onConfigChange: suspend (BiblePresentationSlideConfig) -> Unit
) {
    KeyValueEditor(
        config = mapOf(
//            "Font" to StringKeyValueEditorValueType(
//                canBeBlank = true,
//            ),
//            "Font Color" to IntKeyValueEditorValueType(
//                canBeNull = false,
//                min = 1,
//                max = null,
//            ),
            "Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
            "Verse Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
        ),
        initialValues = mapOf(
            "Font Size" to config.fontSize.toString(),
            "Verse Font Size" to config.verseFontSize.toString(),
        ),
        onSave = {
            onConfigChange(
                BiblePresentationSlideConfig(
                    font = null,
                    fontSize = it["Font Size"]!!.toInt(),
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                    verseFontColor = 0xFFFFFFFF,
                    verseFontSize = it["Verse Font Size"]!!.toInt(),
                    verseFont = null,
                )
            )
        }
    )

}