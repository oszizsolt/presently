package components.presentation.slide

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
import components.presentation.output.window.*
import io.kanro.compose.jetbrains.expui.control.Label
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.PresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.PresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig

@Composable
fun PresentationSlideEditorFactory(
    config: PresentationSlideConfig,
    onConfigChange: suspend (PresentationSlideConfig) -> Unit,
) {
    when (config) {
        is SongPresentationSlideConfig -> SongSlideEditor(
            config = config,
            onConfigChange = onConfigChange,
        )

        is BiblePresentationSlideConfig -> BibleSlideEditor(
            config = config,
            onConfigChange = onConfigChange,
        )

        is StageViewSongPresentationSlideConfig -> StageViewSlideEditor(
            config = config,
            onConfigChange = onConfigChange,
        )
    }
}