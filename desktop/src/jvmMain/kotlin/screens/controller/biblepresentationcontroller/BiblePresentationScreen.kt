package screens.controller.biblepresentationcontroller

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import components.bible.BibleListItem
import components.list.listitem.SelectedState
import components.panel.PanelLayout
import io.presently.service.presentation.PresentationMode
import screens.controller.biblepresentationcontroller.viewmodel.BiblePresentationModeViewModel
import screens.controller.biblepresentationcontroller.viewmodel.BibleSlideViewModel


@Composable
fun BiblePresentationScreen(
    biblePresentationModeViewModel: BiblePresentationModeViewModel,
    bibleSlideViewModel: BibleSlideViewModel,
) {
    PanelLayout {
        val slides by bibleSlideViewModel.slides().collectAsState(emptyList())
        LazyColumn {
            items(slides) { slide ->

                val currentMode by biblePresentationModeViewModel.mode().collectAsState(PresentationMode.Normal)
                val activeSlide by bibleSlideViewModel.active().collectAsState(null)
                val selectedSlide by bibleSlideViewModel.selected().collectAsState(null)

                BibleListItem(
                    selectedState = when {
                        currentMode == PresentationMode.Hidden && slide.id == activeSlide?.id -> SelectedState.SecondarySelected
                        slide.id == activeSlide?.id -> SelectedState.PrimarySelected
                        slide.id == selectedSlide?.id -> SelectedState.TernarySelected
                        else -> SelectedState.NotSelected
                    },
                    onClick = {
                        bibleSlideViewModel.setSlide(slide)
                    },
                    bibleSlide = slide,
                )
            }
        }
    }
}