package com.raihan.configlibrary.core.storage

import android.content.Context
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.util.Preferences

class Receiver {
    companion object {
        private var DATA_RECEIVER: String? = null
    }

    fun get(context: Context, default: String): String {
        if (DATA_RECEIVER == null) {
            DATA_RECEIVER = Preferences(context).get(Constants.PREF.RECEIVER, "")
        }
        return DATA_RECEIVER!!.ifEmpty { default }
    }

    fun set(context: Context, value: String) {
        DATA_RECEIVER = value
        Preferences(context).save(Constants.PREF.RECEIVER, value)
    }
}