package com.raihan.configlibrary.core.model.exception

sealed class ExceptionType(val type: String) {
    data object All : ExceptionType("all")
    data object MainApp : ExceptionType("main_app")
}
