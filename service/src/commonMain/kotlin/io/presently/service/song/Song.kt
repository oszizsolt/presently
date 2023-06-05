package io.presently.service.song

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val id: String,
    val createdAt: Instant,
    val title: String,
    val slides: List<SongSlide>
)
