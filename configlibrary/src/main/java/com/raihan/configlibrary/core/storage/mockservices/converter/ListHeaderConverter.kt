package com.raihan.configlibrary.core.storage.mockservices.converter

import androidx.room.TypeConverter
import com.raihan.configlibrary.core.model.mockservices.ServicesHeader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListHeaderConverter {

    @TypeConverter
    fun listHeaderToString(value: List<ServicesHeader>): String {
        return Gson().toJson(value, object : TypeToken<List<ServicesHeader>>() {}.type)
    }

    @TypeConverter
    fun stringToListHeader(value: String): List<ServicesHeader> {
        return Gson().fromJson(value, object : TypeToken<List<ServicesHeader>>() {}.type)
    }
}