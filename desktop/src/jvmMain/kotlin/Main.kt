import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.ui.window.application
import screens.main.MainWindow


@ExperimentalFoundationApi
fun main() = application {
    MaterialTheme(colors = darkColors()) {
        MainWindow()
    }
}
