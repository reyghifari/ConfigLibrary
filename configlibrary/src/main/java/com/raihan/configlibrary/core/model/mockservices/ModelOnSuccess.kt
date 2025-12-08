package com.raihan.configlibrary.core.model.mockservices

import java.io.File

data class ModelOnSuccess(
    val file: File,
    val fileName: String = "",
    val fileBase64: String = "",
)
