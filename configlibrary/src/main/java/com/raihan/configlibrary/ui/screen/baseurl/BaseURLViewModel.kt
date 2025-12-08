package com.raihan.configlibrary.ui.screen.baseurl

import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import com.raihan.configlibrary.core.model.dropdown.ModelDropdown
import com.raihan.configlibrary.core.storage.BaseURL
import com.raihan.configlibrary.core.storage.SetupPref



class BaseURLViewModel : ViewModel() {

    fun get(context: Context): String = BaseURL().get(context, "null")

    fun save(context: Context, data: String) {
        val newData = when (data) {
            "null" -> ""
            else -> data
        }
        BaseURL().set(context, newData)
    }

    fun setInfo(baseURL: String): AnnotatedString {
        val url = when (baseURL) {
            "null" -> "DEFAULT (sesuai build)"
            else -> baseURL
        }
        return buildAnnotatedString {
            append("Services URL digunakan: \n")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(url)
            }
        }
    }

    fun listENV(context: Context): List<ModelDropdown> {
        return listOf(ModelDropdown("99", "Default", "null"))
            .plus(SetupPref(context).get().baseUrlEnvironment)
    }
}
