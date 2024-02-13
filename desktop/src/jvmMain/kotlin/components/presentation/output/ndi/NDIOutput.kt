package components.presentation.output.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ImageComposeScene
import components.presentation.output.ndi.NDIServer
import io.presently.service.engine.presentationoutput.output.NDIPresentationOutputConfig

@Composable
fun NDIOutput(
    hash: String,
    config: NDIPresentationOutputConfig,
    content: @Composable () -> Unit
) {
    val ndiServer = remember {
        NDIServer(
            name = config.name,
            width = config.width,
            height = config.height,
        )
    }

    DisposableEffect(config.name) {
        onDispose {
            ndiServer.destroy()
        }
    }

    val scene = ImageComposeScene(config.width, config.height) {
        content()
    }

    LaunchedEffect(hash) {
        val image = scene.render()

        ndiServer.draw(image)
    }
}