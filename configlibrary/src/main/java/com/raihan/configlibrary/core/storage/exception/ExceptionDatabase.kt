package com.raihan.configlibrary.core.storage.exception

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExceptionEntity::class], version = 2, exportSchema = false)
abstract class ExceptionDatabase : RoomDatabase() {
    abstract fun dao(): ExceptionDAO
}
