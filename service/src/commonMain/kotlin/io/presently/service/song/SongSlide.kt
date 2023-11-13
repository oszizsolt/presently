package io.presently.service.song

import io.presently.service.engine.Slide
import kotlinx.serialization.Serializable


@Serializable
data class SongSlide(
    val id: String,
    val lines: List<String>,
    val type: SongSlideType,
    val groupId: String,
    val songId: String,
) : Slide