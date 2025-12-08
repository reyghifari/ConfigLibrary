package com.raihan.configlibrary.core.extensions

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import com.raihan.configlibrary.core.base.BaseActivity
import com.raihan.configlibrary.core.storage.SetupPref
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

fun Context.getBaseActivity(): BaseActivity? = this as? BaseActivity

fun Context.activityProcessor(callback: Intent.() -> Unit) {
    val i = Intent().setClassName(
        this,
        "co.id.ist.app_config.ActivityProcess"
    )
    callback(i)
}

fun Context.saveText(text: String, fileName: String? = null): File {
    val fName = fileName ?: "text_${System.currentTimeMillis()}.txt"
    val dest = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fName)
    try {
        dest.createNewFile()
        val fOut = FileOutputStream(dest)
        val myOutWriter = OutputStreamWriter(fOut)
        myOutWriter.append(text)
        myOutWriter.close()
        fOut.flush()
        fOut.close()
    } catch (_: IOException) {}
    return dest
}

fun Context.shareHTML(file: File) {
    try {
        val dataProvider = SetupPref(this).get().packageProvider
        val fileURI = FileProvider.getUriForFile(this, dataProvider, file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.dir/email"
        intent.putExtra(Intent.EXTRA_EMAIL, "")
        intent.putExtra(Intent.EXTRA_STREAM, fileURI)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Exception Logging")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Share Log"))
    } catch (e: Exception) {
        e.printStackTrace()
        println("is exception raises during sending mail $e")
    }
}
