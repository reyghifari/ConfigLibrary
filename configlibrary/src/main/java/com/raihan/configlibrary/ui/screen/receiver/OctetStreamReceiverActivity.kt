package com.raihan.configlibrary.ui.screen.receiver

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.core.app.ShareCompat
import com.raihan.configlibrary.core.model.receiver.FileType
import com.raihan.configlibrary.core.storage.Receiver
import com.raihan.configlibrary.ui.screen.home.HomeActivity
import java.io.File

class OctetStreamReceiverActivity : ComponentActivity() {

    companion object {
        const val EXTRA_HAR = "intent_har"
    }

    override fun onResume() {
        super.onResume()
        checkIntent()
    }

    private fun checkIntent() {
        val i = ShareCompat.IntentReader(this)
        if (i.isShareIntent) {
            if (i.stream != null) {
                val fileType = i.stream!!.fileType()
                when (fileType) {
                    FileType.HAR -> {
                        val cr = contentResolver.openInputStream(i.stream!!)
                        if (cr != null) {
                            val strHar = cr.reader().readText()
                            Receiver().set(this, strHar)
                            intent.removeExtra(Intent.EXTRA_STREAM)
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.putExtra(EXTRA_HAR, true)
                            startActivity(intent)
                            finish()
                        }
                    }

                    FileType.Other -> {}
                }
            }
        }
    }

    private fun Uri.fileType(): FileType {
        val validFileName = listOf(".har")
        var type: FileType = FileType.Other
        if (path != null) {
            val file = File(path!!)
            validFileName.forEach { _ ->
                type = when {
                    file.name.contains(".har", true) -> FileType.HAR
                    else -> FileType.Other
                }
            }
        }
        return type
    }
}