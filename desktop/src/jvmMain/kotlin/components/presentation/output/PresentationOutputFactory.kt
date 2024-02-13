package components.presentation.output

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.presentation.output.window.FullscreenOutput
import components.presentation.output.window.NDIOutput
import components.presentation.output.window.WindowOutput
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig

@Composable
fun PresentationOutputFactory(
    config: PresentationOutputConfig,
    hash: String,
    content: @Composable () -> Unit,
) {

    when (config) {
        is NDIPresentationOutputConfig -> {
            NDIOutput(
                hash = hash,
                config = config,
                content = content,
            )
        }

        is WindowPresentationOutputConfig -> WindowOutput(
            config = config,
            content = content,
        )

        is FullscreenPresentationOutputConfig -> FullscreenOutput(
            config = config,
            content = content,
        )
    }

}