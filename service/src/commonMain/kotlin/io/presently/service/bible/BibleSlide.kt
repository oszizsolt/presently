package io.presently.service.bible

import io.presently.service.engine.Slide
import kotlinx.serialization.Serializable

@Serializable
data class BibleSlide(
    val id: String,
    val book: String,
    val chapter: Int,
    val verse: Int,
    val content: String,
): Slide
