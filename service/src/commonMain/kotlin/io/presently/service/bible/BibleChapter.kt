package io.presently.service.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleChapter(
    val id: String,
    val chapter: Int,
    val verses: List<BibleVerse>
)
