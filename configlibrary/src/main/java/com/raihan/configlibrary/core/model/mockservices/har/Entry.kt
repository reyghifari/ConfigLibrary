package com.raihan.configlibrary.core.model.mockservices.har

data class Entry(
    val startedDateTime: String = "",
    val request: Request = Request(),
    val response: Response = Response(),
)