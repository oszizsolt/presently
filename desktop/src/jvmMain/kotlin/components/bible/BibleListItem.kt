package components.bible

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.preview.PreviewContainer
import components.list.listitem.ListItem
import components.list.listitem.SelectedState
import io.presently.service.bible.BibleSlide

@Composable
fun BibleListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    bibleSlide: BibleSlide
) {
   ListItem(
       modifier = modifier,
       selectedState = selectedState,
       onClick = { onClick() },
       title = bibleSlide.content,
       subtitle = "${bibleSlide.book} ${bibleSlide.chapter}:${bibleSlide.verse}"
   )
}

@Preview
@Composable
fun ListItem_Prev() {
    PreviewContainer {
        Column(
            modifier = Modifier
        ) {
            BibleListItem(
                modifier = Modifier,
                selectedState = SelectedState.NotSelected,
                {},
                bibleSlide = BibleSlide(
                    "_id",
                    "5. Mózes",
                    5,
                    13,
                    "Hat napon át munkálkodjál, és végezd minden dolgodat."
                )
            )
            BibleListItem(
                modifier = Modifier,
                selectedState = SelectedState.NotSelected,
                {},
                bibleSlide = BibleSlide(
                    "_id",
                    "5. Mózes",
                    5,
                    14,
                    "De a hetedik nap az Úrnak, a te Istenednek szombatja: semmi dolgot se tégy azon, se magad, se fiad, se leányod, se szolgád, se szolgálóleányod, se ökröd, se szamarad, és semminémű barmod, se jövevényed, aki a te kapuidon belől van, hogy megnyugodjék a te szolgád és szolgálóleányod, mint te magad;"
                )
            )
        }
    }
}