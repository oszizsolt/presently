package io.presently.service.engine.presentationoutput.output

import kotlinx.serialization.Serializable

@Serializable
sealed interface PresentationOutputConfig {
    val title: String
}