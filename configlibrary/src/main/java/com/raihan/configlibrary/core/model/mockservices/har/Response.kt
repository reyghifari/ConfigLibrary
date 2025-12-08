package com.raihan.configlibrary.core.model.mockservices.har

import com.raihan.configlibrary.core.model.mockservices.har.response.Content

data class Response(
    val status: Int = 200,
    val headers: List<HttpHeader> = listOf(),
    val content: Content = Content(),
)
