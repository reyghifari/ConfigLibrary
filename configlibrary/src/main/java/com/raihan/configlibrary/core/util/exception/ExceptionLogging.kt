package com.raihan.configlibrary.core.util.exception

import android.content.Context
import com.raihan.configlibrary.core.model.exception.ExceptionType
import com.raihan.configlibrary.core.storage.SetupPref
import com.raihan.configlibrary.core.storage.exception.ExceptionEntity

class ExceptionLogging(private val context: Context) : Thread.UncaughtExceptionHandler {

    companion object {
        const val EXTRA_DATA = "data_exception"
    }

    private val defaultUEH = Thread.getDefaultUncaughtExceptionHandler()
    private val fileType get() = ".kt|java:"
    private val appID get() = SetupPref(context).get().packageException

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, event: Throwable) {
        val date = System.currentTimeMillis()
        val exceptionData = ExceptionEntity(
            id = date,
            dateMillis = date,
            message = event.stackTraceToString()
        )
        var isLogging = false
        exceptionData.message
            .split("\n")
            .filter { it.isMainFile() }
            .forEach {
                if (isLogging.not()) {
                    val fileName = it.substringAfter("(").substringBefore(":")
                    exceptionData.fileName = fileName
                    exceptionData.lineNumber = it.substringAfter("$fileName:").substringBefore(")")
                    exceptionData.className = it.replaceBefore(appID, "").substringBefore("(")
                    exceptionData.exceptionType = ExceptionType.MainApp.type
                    isLogging = true
                }
            }
        if (isLogging.not()) {
            exceptionData.message
                .split("\n")
                .filter { it.isAndroidFile() }
                .forEach {
                    if (isLogging.not()) {
                        val fileName = it.substringAfter("(").substringBefore(":")
                        exceptionData.fileName = fileName
                        exceptionData.lineNumber =
                            it.substringAfter("$fileName:").substringBefore(")")
                        exceptionData.className = it.replaceBefore(appID, "").substringBefore("(")
                        exceptionData.exceptionType = ExceptionType.All.type
                        isLogging = true
                    }
                }
        }
        defaultUEH?.uncaughtException(thread, event)
    }

    private fun String.isMainFile() = contains(appID) && isAndroidFile()
    private fun String.isAndroidFile() = contains(Regex(fileType))
}
