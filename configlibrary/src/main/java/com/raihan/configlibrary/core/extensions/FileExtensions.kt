package com.raihan.configlibrary.core.extensions

import android.util.Base64
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

fun File.base64(): String {
    if (length() > Int.MAX_VALUE) {
        return ""
    }
    val inStream: InputStream = FileInputStream(this)
    val bytes = ByteArray(length().toInt())
    var offset = 0
    var numRead = 0
    while (offset < bytes.size && inStream.read(bytes, offset, bytes.size - offset)
            .also { numRead = it } >= 0
    ) {
        offset += numRead
    }
    if (offset < bytes.size) {
        throw IOException("Could not completely read file $name")
    }
    inStream.close()
    return Base64.encodeToString(bytes, Base64.NO_WRAP or Base64.CRLF)
}
