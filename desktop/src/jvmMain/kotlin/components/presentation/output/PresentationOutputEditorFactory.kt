package components.presentation.output

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.presentation.output.window.FullscreenOutputEditor
import components.presentation.output.window.NDIOutputEditor
import components.presentation.output.window.WindowOutput
import components.presentation.output.window.WindowOutputEditor
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig

@Composable
fun PresentationOutputEditorFactory(
    config: PresentationOutputConfig,
    onConfigChange: (PresentationOutputConfig) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(vertical = 24.dp)
    ) {
        Label(
            text = "Output settings",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )

        Box(Modifier.height(12.dp))

        when (config) {
            is NDIPresentationOutputConfig -> NDIOutputEditor(
                config = config,
                onConfigChange = onConfigChange,
            )

            is WindowPresentationOutputConfig -> WindowOutputEditor(
                config = config,
                onConfigChange = onConfigChange,
            )

            is FullscreenPresentationOutputConfig -> FullscreenOutputEditor(
                config = config,
                onConfigChange = onConfigChange,
            )
        }

        // TODO add save button

        // TODO add error handling

//        Button({
//            onConfigChange(it)
//        }) {
//            Label("save")
//        }
    }
}