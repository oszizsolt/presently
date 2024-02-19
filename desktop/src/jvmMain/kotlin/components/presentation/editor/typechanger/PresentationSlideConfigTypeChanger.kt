package components.presentation.editor.typechanger

import androidx.compose.runtime.Composable
import components.presentation.editor.keyvalueeditor.EnumKeyValueEditorValueType
import components.presentation.editor.keyvalueeditor.KeyValueEditor
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig

@Composable
fun PresentationSlideConfigTypeChanger(
    current: PresentationSlideConfig,
    onChange: suspend (PresentationSlideConfig) -> Unit
) {
    val titles = listOf(
        "Bible",
        "Song",
        "Stage View",
    )

    KeyValueEditor(
        title = "Select Slide Type",
        config = mapOf(
            "Type" to EnumKeyValueEditorValueType(
                values = titles.associateWith { it!! }
            )
        ),
        initialValues = mapOf(
            "Type" to current.title,
        ),
        onSave = {
            val value = it["Type"]!!

            val newValue = when (value) {
                current.title -> current
                "Bible" -> BiblePresentationSlideConfig(
                    font = null,
                    fontSize = 18,
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                    verseFontColor = 0xFFFFFFFF,
                    verseFontSize = 13,
                    verseFont = null,
                )

                "Song" -> SongPresentationSlideConfig(
                    font = null,
                    fontSize = 18,
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                )

                "Stage View" -> StageViewSongPresentationSlideConfig(
                    font = null,
                    fontSize = 18,
                    fontColor = 0xFFFFFFFF,
                    backgroundColor = 0xFF000000,
                    previewFont = null,
                    previewFontSize = 15,
                    previewFontColor = 0xFFFFFFFF,
                )

                else -> current
            }

            if (newValue != current) {
                onChange(newValue)
            }
        }
    )
}