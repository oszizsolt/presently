package io.presently.service.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleVerse(
    val id: String,
    val slides: List<BibleSlide>,
)
