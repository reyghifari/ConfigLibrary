package com.raihan.configlibrary.ui.screen.exception

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.configlibrary.core.model.dropdown.ModelDropdown
import com.raihan.configlibrary.core.model.exception.ExceptionType
import com.raihan.configlibrary.core.storage.SetupPref
import com.raihan.configlibrary.core.storage.exception.ExceptionDB
import com.raihan.configlibrary.core.storage.exception.ExceptionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExceptionViewModel : ViewModel() {
    companion object {
        fun preview(): ExceptionViewModel {
            return ExceptionViewModel().apply {
                // isi dummy state untuk Preview
            }
        }
    }
    var stateListException by mutableStateOf(listOf<ExceptionEntity>())
    var selected by mutableStateOf(ExceptionEntity(0L, 0L))
    var exceptionType: String by mutableStateOf(ExceptionType.All.type)

    fun getExceptionLog(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAll().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun getMainAppExceptionLog(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAllMainApp().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun getExceptionBaseOnDate(
        context: Context,
        start: Long,
        end: Long,
        callback: (List<ExceptionEntity>) -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAllBetweenDate(start, end).also {
                viewModelScope.launch {
                    callback(it)
                }
            }
        }
    }

    fun deleteExceptionLog(context: Context, ids: List<Long>) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().delete(ids)
            ExceptionDB.getInstance(context).dao().getAll().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun updateException(context: Context, type: String = exceptionType) {
        exceptionType = type
        when (type) {
            ExceptionType.All.type -> getExceptionLog(context)
            ExceptionType.MainApp.type -> getMainAppExceptionLog(context)
        }
    }

    fun exceptionTypeList(context: Context): List<ModelDropdown> {
        val packageException = SetupPref(context).get().packageException
        return listOf(
            ModelDropdown("0", "All", ExceptionType.All.type),
            ModelDropdown("1", packageException, ExceptionType.MainApp.type),
            )
    }
}