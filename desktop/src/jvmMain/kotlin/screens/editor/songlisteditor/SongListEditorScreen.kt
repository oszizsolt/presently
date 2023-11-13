package screens.editor.songlisteditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.window.ApplicationScope
import io.kanro.compose.jetbrains.expui.control.Icon
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.presently.service.list.SongList
import io.presently.service.presentation.song.SongPresentationServiceImplementation
import io.presently.service.song.Song
import io.presently.service.song.SongSlide
import io.presently.service.song.SongSlideType
import kotlinx.datetime.Clock
import screens.controller.songpresentationcontroller.SongPresentationControllerWindow
import screens.controller.songpresentationcontroller.viewmodel.SongControllerViewModel
import screens.controller.songpresentationcontroller.viewmodel.SongListControllerViewModel
import screens.controller.songpresentationcontroller.viewmodel.SongPresentationModeViewModel
import screens.controller.songpresentationcontroller.viewmodel.SongSlideControllerViewModel
import screens.main.LocalEngine

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongListEditorScreen() {
    val coroutineScope = rememberCoroutineScope()

    val presentationEngine = LocalEngine.current

    var isPresenting by remember { mutableStateOf(false) }

    Column {
        PrimaryButton(onClick = {

        }) {
            Icon("icons/debug_dark.svg")
        }

        PrimaryButton(
            onClick = {
                isPresenting = !isPresenting
            },
            enabled = isPresenting || presentationEngine != null,
        ) {
            Icon(
                if (isPresenting) {
                    "icons/stop_dark.svg"
                } else {
                    "icons/run_dark.svg"
                }
            )
        }

        if (isPresenting && presentationEngine != null) {
            val songPresentationService = SongPresentationServiceImplementation(
                engine = presentationEngine,
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
                onClose = {
                    isPresenting = false
                }
            )
        }
    }

}