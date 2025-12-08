package com.raihan.configlibrary.core.storage.exception

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.extensions.encrypt

class ExceptionDB {
    companion object {
        private var INSTANCE: ExceptionDatabase? = null

        fun getInstance(context: Context): ExceptionDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ExceptionDatabase::class.java,
                        Constants.DATABASE.TB_EXCEPTION
                    )
                    .addMigrations(migrationV1toV2)
                    .encrypt()
                    .build()
            }
            return INSTANCE!!
        }

        private val migrationV1toV2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ${Constants.DATABASE.TB_EXCEPTION} ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}