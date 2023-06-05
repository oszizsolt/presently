package io.presently.service.song

import kotlinx.serialization.Serializable


@Serializable
data class SongSlide(
    val id: String,
    val lines: List<String>,
    val type: SongSlideType,
    val groupId: String,
)