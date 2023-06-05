package io.presently.service.song

import kotlinx.serialization.Serializable

@Serializable
enum class SongSlideType {
    Normal,
    Comment,
}