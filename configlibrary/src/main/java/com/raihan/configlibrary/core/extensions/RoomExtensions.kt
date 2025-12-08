package com.raihan.configlibrary.core.extensions

import androidx.room.RoomDatabase
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import java.nio.charset.Charset

fun <T : RoomDatabase> RoomDatabase.Builder<T>.encrypt(): RoomDatabase.Builder<T> {
    val password = getBytes("hpHxATOP84XaoihLRGmy")
    val factory = SupportOpenHelperFactory(password)
    openHelperFactory(factory)
    return this
}

private fun getBytes(data: String?): ByteArray {
    if (data == null || data.isEmpty()) return byteArrayOf()
    return data.toByteArray(Charset.forName("UTF-8"))
}