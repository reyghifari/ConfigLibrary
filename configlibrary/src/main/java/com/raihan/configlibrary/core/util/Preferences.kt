package com.raihan.configlibrary.core.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

internal class Preferences(val context: Context) {

    companion object {
        private var INSTANCE: SharedPreferences? = null
    }

    private fun pref(): SharedPreferences {
        if (INSTANCE == null) {
            INSTANCE = context.getSharedPreferences("app_config_prefs_v2", Activity.MODE_PRIVATE)
        }
        return INSTANCE!!
    }

    inline fun <reified T> save(key: String, value: T): Boolean {
        val editor = pref().edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }
        return editor.commit()
    }

    inline fun <reified T> get(key: String, default: T): T {
        return when (default) {
            is String -> pref().getString(key, default) as T
            is Boolean -> pref().getBoolean(key, default) as T
            else -> default
        }
    }
}