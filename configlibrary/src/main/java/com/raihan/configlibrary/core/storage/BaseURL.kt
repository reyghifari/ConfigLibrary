package com.raihan.configlibrary.core.storage

import android.content.Context
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.util.Preferences

class BaseURL {
    companion object {
        private var BASE_URL: String? = null
    }

    fun get(context: Context, default: String): String {
        if (BASE_URL == null) {
            BASE_URL = Preferences(context).get(Constants.PREF.BASE_URL, "")
        }
        return BASE_URL!!.ifEmpty { default }
    }

    fun set(context: Context, value: String) {
        BASE_URL = value
        Preferences(context).save(Constants.PREF.BASE_URL, value)
    }
}