package com.raihan.configlibrary.core.util.exception

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.raihan.configlibrary.core.storage.exception.ExceptionDB
import com.raihan.configlibrary.core.storage.exception.ExceptionEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExceptionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataStr = intent.getStringExtra(ExceptionLogging.EXTRA_DATA) ?: "{}"
            val exceptionData = Gson().fromJson(dataStr, ExceptionEntity::class.java)
            ExceptionDB.getInstance(context.applicationContext).dao().insert(exceptionData)
        }
    }
}