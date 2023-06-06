import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.window.application
import io.presently.service.list.SongList
import io.presently.service.presentation.song.SongPresentationServiceImplementation
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import io.presently.service.song.SongSlideType
import kotlinx.datetime.Clock
import screens.songpresentationcontroller.SongPresentationControllerViewModel
import screens.songpresentationcontroller.SongPresentationControllerWindow


@ExperimentalFoundationApi
fun main() = application {
    val songPresentationService = SongPresentationServiceImplementation(
        list = SongList(
            id = "l-1",
            title = "1",
            createdAt = Clock.System.now(),
            songs = listOf(
                Song(
                    id = "s-1",
                    createdAt = Clock.System.now(),
                    title = "A csillagok fölött",
                    slides = listOf(
                        SongSlide(
                            id = "ss-1",
                            lines = listOf(
                                "A csillagok fölött",
                                "Király él, az örök"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-1",
                            songId = "s-1",
                        ),
                        SongSlide(
                            id = "ss-2",
                            lines = listOf(
                                "Hangja, mint tenger zúg",
                                "Szava sziklákat zúz"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-1",
                            songId = "s-1",
                        ),
                        SongSlide(
                            id = "ss-3",
                            lines = listOf(
                                "Felhőbe öltözött",
                                "Ül fény és tűz között"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2",
                            songId = "s-1",
                        ),
                        SongSlide(
                            id = "ss-4",
                            lines = listOf(
                                "Szól, s hátrál a sötét",
                                "Remeg a mindenség"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2",
                            songId = "s-1",
                        )
                    )
                ),
                Song(
                    id = "s-2",
                    createdAt = Clock.System.now(),
                    title = "Hullj eső, halkan szitálj",
                    slides = listOf(
                        SongSlide(
                            id = "ss-2-1",
                            lines = listOf(
                                "Hullj eső, halkan szitálj",
                                "Kiszáradt szívvel a világ vár"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2-1",
                            songId = "s-2",
                        ),
                        SongSlide(
                            id = "ss-2-2",
                            lines = listOf(
                                "Elég volt a fájdalom",
                                "Amelyben teltek évszázadok"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2-1",
                            songId = "s-2",
                        ),
                        SongSlide(
                            id = "ss-2-3",
                            lines = listOf(
                                "Hullj eső, csak szállj le ránk",
                                "Mint örömkönnyek milliók arcán"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2-2",
                            songId = "s-2",
                        ),
                        SongSlide(
                            id = "ss-2-4",
                            lines = listOf(
                                "Nézd, a Föld pár búcsúkört",
                                "Még ír az égen, csak végre jöjj"
                            ),
                            type = SongSlideType.Normal,
                            groupId = "g-2-1",
                            songId = "s-2",
                        )
                    )
                ),
            ),
        )
    )

    SongPresentationControllerWindow(
        songPresentationService = songPresentationService,
        songPresentationControllerViewModel = SongPresentationControllerViewModel(),
    )
}
