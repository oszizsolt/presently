package components.presentation.output

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import components.presentation.output.WindowOutput
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig

@Composable
fun PresentationOutputFactory(
    config: PresentationOutputConfig,
    content: @Composable () -> Unit,
) {
    when (config) {
        is NDIPresentationOutputConfig -> {
            TODO()
        }
        is WindowPresentationOutputConfig -> WindowOutput(
            config = config,
            content = content,
        )
    }
}