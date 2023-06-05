package io.presently.service.list

import io.presently.service.song.Song
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class SongList(
    val title: String,
    val createdAt: Instant,
    val songs: List<Song>,
)
