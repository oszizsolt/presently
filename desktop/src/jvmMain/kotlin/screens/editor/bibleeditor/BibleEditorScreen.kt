package screens.editor.bibleeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import io.kanro.compose.jetbrains.expui.control.Icon
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.presently.service.bible.*
import io.presently.service.presentation.bible.BiblePresentationServiceImplementation
import screens.controller.biblepresentationcontroller.BiblePresentationControllerWindow
import screens.controller.biblepresentationcontroller.viewmodel.BiblePresentationModeViewModel
import screens.controller.biblepresentationcontroller.viewmodel.BibleSlideViewModel
import screens.main.LocalEngine

@Composable
fun BibleEditorScreen() {
    val coroutineScope = rememberCoroutineScope()

    val presentationEngine = LocalEngine.current

    var isPresenting by remember { mutableStateOf(false) }

    Column {
        PrimaryButton(onClick = {

        }) {
            Icon("icons/debug_dark.svg")
        }

        PrimaryButton(
            enabled = isPresenting || presentationEngine != null,
            onClick = {
                isPresenting = !isPresenting
            }
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

            val biblePresentationService = BiblePresentationServiceImplementation(
                engine = presentationEngine,
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


            BiblePresentationControllerWindow(
                biblePresentationModeViewModel = BiblePresentationModeViewModel(
                    coroutineScope = coroutineScope,
                    biblePresentationService = biblePresentationService,
                ),
                bibleSlideViewModel = BibleSlideViewModel(
                    coroutineScope = coroutineScope,
                    biblePresentationService = biblePresentationService,
                ),
                onClose = {
                    isPresenting = false
                }
            )
        }
    }
}