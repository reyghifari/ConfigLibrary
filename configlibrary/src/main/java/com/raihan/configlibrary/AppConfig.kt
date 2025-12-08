package com.raihan.configlibrary

import android.content.Context
import com.raihan.configlibrary.core.model.dropdown.ModelDropdown
import com.raihan.configlibrary.core.storage.BaseURL
import com.raihan.configlibrary.core.storage.SetupPref
import com.raihan.configlibrary.core.util.ConfigNotification
import com.raihan.configlibrary.core.util.exception.ExceptionLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AppConfig {
    fun init(context: Context, destinationPackage: String, listUrl: List<String>) {
        SetupPref(context).set {
            preferencesName = "app_pref"
            packageException = "co.id.reyghifari.appconfig"
            packageProvider = "co.id.reyghifari.fileprovider"
            resetFinishDestination = destinationPackage
            baseUrlEnvironment = listUrl.mapIndexed { index, url ->
                ModelDropdown(
                    id = index.toString(),
                    label = "ENV_$index",
                    value = url
                )
            }
        }
        ConfigNotification.init(context)
    }

    fun baseURL(context: Context, default: String): String = BaseURL().get(context, default)

    fun exceptionLog(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionLogging(context).init()
        }
    }
}