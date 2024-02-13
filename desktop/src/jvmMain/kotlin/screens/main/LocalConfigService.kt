package screens.main

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import io.presently.service.config.Config
import io.presently.service.config.ConfigService
import io.presently.service.engine.presentationoutput.OutputConfig
import io.presently.service.engine.presentationoutput.output.FullscreenPresentationOutputConfig
import io.presently.service.engine.presentationoutput.output.WindowPresentationOutputConfig
import io.presently.service.engine.presentationoutput.slide.BiblePresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.SongPresentationSlideConfig
import io.presently.service.engine.presentationoutput.slide.StageViewSongPresentationSlideConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import service.config.DbConfigService

val LocalCurrentConfig = compositionLocalOf<Config?> {
    null
}

val LocalConfigService = compositionLocalOf<ConfigService> {
    val service = DbConfigService()

    GlobalScope.launch {
        service.put(
            Config(
                name = "Test 1",
                outputs = listOf(
                    OutputConfig(
                        name = "Test Stage View",
                        outputConfig = WindowPresentationOutputConfig(
                            width = 640,
                            height = 480,
                            resizable = false,
                        ),
                        slideConfig = StageViewSongPresentationSlideConfig(
                            font = null,
                            previewFont = null,
                            fontSize = 18,
                            previewFontSize = 16,
                            fontColor = 0xffffffffL,
                            previewFontColor = 0xffffffffL,
                            backgroundColor = 0xff000000L,
                        ),
                    ),
                    OutputConfig(
                        name = "Test Bible",
                        outputConfig = WindowPresentationOutputConfig(
                            width = 640,
                            height = 480,
                            resizable = false,
                        ),
                        slideConfig = BiblePresentationSlideConfig(
                            font = null,
                            fontSize = 18,
                            fontColor = 0xffffffffL,
                            backgroundColor = 0xff000000L,
                            verseFontColor = 0xffffffffL,
                            verseFontSize = 14,
                            verseFont = null,
                        ),
                    ),
                )
            )
        )

        service.put(
            Config(
                name = "Test 2",
                outputs = listOf(
                    OutputConfig(
                        name = "Test Song",
                        outputConfig = WindowPresentationOutputConfig(
                            width = 640,
                            height = 480,
                            resizable = false,
                        ),
                        slideConfig = SongPresentationSlideConfig(
                            font = null,
                            fontSize = 18,
                            fontColor = 0xffffffffL,
                            backgroundColor = 0xff000000L,
                        ),
                    ),
                )
            )
        )

        service.put(
            Config(
                name = "Fullscreen test",
                outputs = listOf(
                    OutputConfig(
                        name = "Test Song",
                        outputConfig = FullscreenPresentationOutputConfig(
                            displayId = null,
                        ),
                        slideConfig = SongPresentationSlideConfig(
                            font = null,
                            fontSize = 18,
                            fontColor = 0xffffffffL,
                            backgroundColor = 0xff000000L,
                        ),
                    ),
                )
            )
        )
    }

    service
}

