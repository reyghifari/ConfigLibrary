package com.raihan.configlibrary.core.storage.mockservices

import android.content.Context
import androidx.room.Room
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.extensions.encrypt

class MockServices {

    companion object {
        private var INSTANCE: MockServicesDatabase? = null

        fun getInstance(context: Context): MockServicesDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        MockServicesDatabase::class.java,
                        Constants.DATABASE.TB_MOCK_SERVICES
                    )
                    .encrypt()
                    .build()
            }
            return INSTANCE!!
        }
    }
}