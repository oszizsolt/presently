package io.presently.service.engine.presentationoutput.output

data class NDIPresentationOutputConfig(
    val name: String,
    val height: Int,
    val width: Int,
) : PresentationOutputConfig