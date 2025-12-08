package com.raihan.configlibrary.core.extensions

import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern

fun String.regexReplaceAll(regex: String, replace: String = ""): String {
    return Pattern
        .compile(regex)
        .matcher(this)
        .replaceAll(replace)
}

fun String.getFirstLater(): String {
    if (this.isEmpty()) return "-"
    return split(" ")
        .filterIndexed { i, _ -> i < 2 }
        .joinToString("") {
            when {
                it.isNotEmpty() -> it.first().uppercase()
                else -> ""
            }
        }
}

fun String.validJSON(): Boolean {
    return try {
        JSONObject(this)
        true
    } catch (_: JSONException) {
        false
    }
}

fun String.cleanJSON(): String {
    val removeNBSP = this.replace("\u00a0", " ")
    return removeNBSP
}