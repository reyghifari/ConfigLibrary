package com.raihan.configlibrary.core.model.mockservices.har

data class Log(
    val version: String = "1.0.0",
    val creator: Creator = Creator(),
    val entries: List<Entry> = listOf(),
)
