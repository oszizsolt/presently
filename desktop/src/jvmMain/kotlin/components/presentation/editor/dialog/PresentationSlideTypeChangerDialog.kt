package components.presentation.editor.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import components.presentation.editor.typechanger.PresentationSlideConfigTypeChanger
import components.presentation.output.PresentationOutputEditorFactory
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.presently.service.engine.presentationoutput.OutputConfig
import kotlinx.coroutines.launch


@Composable
fun PresentationSlideTypeChangerDialogButton(
    config: OutputConfig,
    onSave: suspend (OutputConfig) -> Unit,
) {
    var isOpen by remember { mutableStateOf(false) }

    if (isOpen) {
        DialogWindow(
            visible = isOpen,
            title = "Slide Type",
            onCloseRequest = {
                isOpen = false
            }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(DarkTheme.NormalAreaColors.startBackground)
            ) {
                PresentationSlideConfigTypeChanger(
                    current = config.slideConfig,
                    onChange = {
                        onSave(
                            config.copy(
                                slideConfig = it,
                            )
                        )

                        isOpen = false
                    }
                )
            }

        }
    }

    Box(
        modifier = Modifier.padding(horizontal = 4.dp),
    ) {
        PrimaryButton(
            onClick = {
                isOpen = true
            }
        ) {
            Label("Change Type")
        }
    }

}