package components.presentation.output.window

import androidx.compose.runtime.*
import components.presentation.editor.keyvalueeditor.IntKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig

@Composable
fun StageViewSlideEditor(
    config: StageViewSongPresentationSlideConfig,
    onConfigChange: suspend (StageViewSongPresentationSlideConfig) -> Unit
) {
    //  val font: String?,
    //    val previewFont: String?,
    //    val fontSize: Int,
    //    val previewFontSize: Int,
    //    val fontColor: Long,
    //    val previewFontColor: Long,
    //    val backgroundColor: Long,

    KeyValueEditor(
        title = "${config.title} Settings",
        config = mapOf(
            "Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
            "Preview Font Size" to IntKeyValueEditorValueType(
                canBeNull = false,
                min = 1,
                max = null,
            ),
        ),
        initialValues = mapOf(
            "Font Size" to config.fontSize.toString(),
            "Preview Font Size" to config.previewFontSize.toString(),
        ),
        onSave = {
            onConfigChange(
                StageViewSongPresentationSlideConfig(
                    font = null,
                    fontSize = it["Font Size"]!!.toInt(),
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                    previewFont = null,
                    previewFontColor = 0xFFFFFFFF,
                    previewFontSize = it["Preview Font Size"]!!.toInt()
                )
            )
        }
    )

}