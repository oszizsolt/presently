import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import io.presently.service.bible.*
import io.presently.service.list.SongList
import io.presently.service.presentation.bible.BiblePresentationServiceImplementation
import io.presently.service.presentation.song.SongPresentationServiceImplementation
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import io.presently.service.song.SongSlideType
import kotlinx.datetime.Clock
import screens.biblepresentationcontroller.BiblePresentationControllerWindow
import screens.biblepresentationcontroller.viewmodel.BiblePresentationModeViewModel
import screens.biblepresentationcontroller.viewmodel.BibleSlideViewModel
import screens.songpresentationcontroller.viewmodel.SongControllerViewModel
import screens.songpresentationcontroller.SongPresentationControllerWindow
import screens.songpresentationcontroller.viewmodel.SongListControllerViewModel
import screens.songpresentationcontroller.viewmodel.SongPresentationModeViewModel
import screens.songpresentationcontroller.viewmodel.SongSlideControllerViewModel


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


    val biblePresentationService = BiblePresentationServiceImplementation(
        books = mapOf(
            BibleTranslation(
                id = "HUNK",
                shortName = "HUNK",
                fullName = "Magyar KAR",
            ) to listOf(
                BibleBook(
                    id = "HUNK_1MOZ",
                    name = "I. Mózes",
                    chapters = listOf(
                        BibleChapter(
                            id = "HUNK_1MOZ_1",
                            chapter = 1,
                            verses = listOf(
                                BibleVerse(
                                    id = "HUNK_1MOZ_1_1",
                                    verse = 1,
                                    slides = listOf(
                                        BibleSlide(
                                            id = "HUNK_1MOZ_1_1_1",
                                            book = "I. Mózes",
                                            chapter = 1,
                                            verse = 1,
                                            content = "Kezdetben teremté Isten az eget és a földet."
                                        )
                                    ),
                                ),
                                BibleVerse(
                                    id = "HUNK_1MOZ_1_2",
                                    verse = 2,
                                    slides = listOf(
                                        BibleSlide(
                                            id = "HUNK_1MOZ_1_2_1",
                                            book = "I. Mózes",
                                            chapter = 1,
                                            verse = 2,
                                            content = "A föld pedig kietlen és puszta vala, és setétség vala a mélység színén, és az Isten Lelke lebeg vala a vizek felett."
                                        )
                                    ),
                                ),
                                BibleVerse(
                                    id = "HUNK_1MOZ_1_11",
                                    verse = 11,
                                    slides = listOf(
                                        BibleSlide(
                                            id = "HUNK_1MOZ_1_11_1",
                                            book = "I. Mózes",
                                            chapter = 1,
                                            verse = 11,
                                            content = "Azután monda Isten: Hajtson a föld gyenge füvet, maghozó füvet, gyümölcsfát, amely gyümölcsöt hozzon az ő neme szerint, amelyben legyen néki magva e földön."
                                        ),
                                        BibleSlide(
                                            id = "HUNK_1MOZ_1_11_2",
                                            book = "I. Mózes",
                                            chapter = 1,
                                            verse = 11,
                                            content = "És úgy lőn."
                                        )
                                    ),
                                ),
                            )
                        )
                    )
                )
            )
        )
    )


    val coroutineScope = rememberCoroutineScope()

    BiblePresentationControllerWindow(
        biblePresentationModeViewModel = BiblePresentationModeViewModel(
            coroutineScope = coroutineScope,
            biblePresentationService = biblePresentationService,
        ),
        bibleSlideViewModel = BibleSlideViewModel(
            coroutineScope = coroutineScope,
            biblePresentationService = biblePresentationService,
        )
    )

    SongPresentationControllerWindow(
        songControllerViewModel = SongControllerViewModel(
            coroutineScope = coroutineScope,
            songPresentationService = songPresentationService,
        ),
        songSlideControllerViewModel = SongSlideControllerViewModel(
            coroutineScope = coroutineScope,
            songPresentationService = songPresentationService,
        ),
        songListControllerViewModel = SongListControllerViewModel(
            coroutineScope = coroutineScope,
            songPresentationService = songPresentationService,
        ),
        songPresentationModeViewModel = SongPresentationModeViewModel(
            coroutineScope = coroutineScope,
            songPresentationService = songPresentationService,
        ),
    )
}
