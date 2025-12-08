package com.raihan.configlibrary.core.storage.mockservices

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raihan.configlibrary.core.storage.mockservices.converter.ListHeaderConverter

@Database(entities = [MockServicesEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListHeaderConverter::class)
abstract class MockServicesDatabase : RoomDatabase() {
    abstract fun dao(): MockServicesDAO
}
