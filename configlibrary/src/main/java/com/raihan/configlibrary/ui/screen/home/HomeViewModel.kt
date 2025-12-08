package com.raihan.configlibrary.ui.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.configlibrary.core.storage.mockservices.MockServices
import com.raihan.configlibrary.core.storage.mockservices.MockServicesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun saveMockServices(context: Context, datas: List<MockServicesEntity>, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            datas.forEach {
                MockServices.getInstance(context).dao().insert(it)
            }
            viewModelScope.launch {
                callback()
            }
        }
    }
}