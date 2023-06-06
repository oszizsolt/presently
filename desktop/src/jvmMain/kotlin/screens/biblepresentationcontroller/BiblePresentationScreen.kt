package screens.biblepresentationcontroller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import screens.biblepresentationcontroller.viewmodel.BiblePresentationModeViewModel
import screens.biblepresentationcontroller.viewmodel.BibleSlideViewModel


@Composable
fun BiblePresentationScreen(
    biblePresentationModeViewModel: BiblePresentationModeViewModel,
    bibleSlideViewModel: BibleSlideViewModel,
) {
    Row {
        Column {
            val slides by bibleSlideViewModel.slides().collectAsState(emptyList())
            LazyColumn {

                items(slides) { slide ->

                }
            }
        }
    }
}