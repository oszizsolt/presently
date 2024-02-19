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
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig
import java.awt.GraphicsEnvironment

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