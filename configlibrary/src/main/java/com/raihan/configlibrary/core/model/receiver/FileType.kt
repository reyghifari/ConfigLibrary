package com.raihan.configlibrary.core.model.receiver

sealed class FileType() {
    data object HAR : FileType()
    data object Other : FileType()
}