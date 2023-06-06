package io.presently.service.bible

import kotlinx.serialization.Serializable


@Serializable
data class BibleBook(
    val id: String,
    val name: String,
    val chapters: List<BibleChapter>,
)
