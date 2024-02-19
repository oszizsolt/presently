package components.presentation.editor

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.Window
import common.preview.PreviewContainer
import components.presentation.editor.dialog.PresentationOutputEditorDialogButton
import components.presentation.editor.dialog.PresentationOutputTypeChangerDialogButton
import components.presentation.editor.dialog.PresentationSlideEditorDialogButton
import components.presentation.editor.dialog.PresentationSlideTypeChangerDialogButton
import components.presentation.output.PresentationOutputEditorFactory
import components.presentation.slide.PresentationSlideEditorFactory
import components.presentation.slide.PresentationSlideFactory
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.theme.LightTheme
import io.kanro.compose.jetbrains.expui.theme.Theme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import io.presently.service.engine.presentationoutput.OutputConfig
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import kotlinx.coroutines.launch

@Composable
fun PresentationEditorEntry(
    config: OutputConfig,
    onSave: suspend (config: OutputConfig) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            Modifier.padding(bottom = 4.dp)
        ) {
            Label(
                text = config.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                // TODO icon here

                Label(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = config.slideConfig.title,
                )

                Row {
                    PresentationSlideTypeChangerDialogButton(
                        config = config,
                        onSave = onSave,
                    )

                    PresentationSlideEditorDialogButton(
                        config = config,
                        onSave = onSave
                    )
                }
            }

            Box(
                Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(DarkTheme.NormalAreaColors.startBorderColor)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                // TODO icon here

                Label(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = config.outputConfig.title,
                )

                Row {
                    PresentationOutputTypeChangerDialogButton(
                        config = config,
                        onSave = onSave,
                    )

                    PresentationOutputEditorDialogButton(
                        config = config,
                        onSave = onSave
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PresentationEditorEntry_Preview() {
    PreviewContainer {
        Column {
            PresentationEditorEntry(
                OutputConfig(
                    name = "Config 1",
                    outputConfig = FullscreenPresentationOutputConfig(
                        displayId = null,
                    ),
                    slideConfig = SongPresentationSlideConfig(
                        font = null,
                        fontSize = 14,
                        fontColor = 0xFFFFFFFF,
                        backgroundColor = 0xFF000000,
                    )
                )
            ) {

            }

            PresentationEditorEntry(
                OutputConfig(
                    name = "Config 2",
                    outputConfig = FullscreenPresentationOutputConfig(
                        displayId = null,
                    ),
                    slideConfig = SongPresentationSlideConfig(
                        font = null,
                        fontSize = 14,
                        fontColor = 0xFFFFFFFF,
                        backgroundColor = 0xFF000000,
                    )
                )
            ) {

            }
        }
    }
}