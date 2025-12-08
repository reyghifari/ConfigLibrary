package com.raihan.configlibrary.core.model.mockservices.har

import com.raihan.configlibrary.core.model.mockservices.har.request.PostData

data class Request(
    val method: String = "",
    val url: String = "",
    val headers: List<HttpHeader> = listOf(),
    val postData: PostData = PostData(),
)