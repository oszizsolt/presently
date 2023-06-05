package io.presently.service.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleTranslation(
    val id: String,
    val shortName: String,
    val fullName: String,
)