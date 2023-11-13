package io.presently.service.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleVerse(
    val id: String,
    val verse: Int,
    val slides: List<BibleSlide>,
)
