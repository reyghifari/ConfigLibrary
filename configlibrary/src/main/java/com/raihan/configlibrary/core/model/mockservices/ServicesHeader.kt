package com.raihan.configlibrary.core.model.mockservices

data class ServicesHeader(
    val id: Long = 0L,
    var isHeaderResponse: Boolean = true,
    var key: String = "",
    var value: String = "",
)