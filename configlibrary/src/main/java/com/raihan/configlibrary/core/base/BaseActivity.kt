package com.raihan.configlibrary.core.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.raihan.configlibrary.core.util.CheckPermission
import com.raihan.configlibrary.core.util.WindowManagerCompact
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import java.io.File

abstract class BaseActivity : ComponentActivity() {

    private val filePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data: Uri? = it.data?.data
                if (data != null) {
                    val proj = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(data, proj, null, null, null)
                    val column = cursor?.getColumnIndex(MediaStore.Images.Media.DATA)
                    cursor?.moveToFirst()
                    if (column != null) {
                        val filePath = cursor.getString(column)
                        onSuccessFileLaunch(File(filePath))
                    }
                }
            }
        }
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it == true) {
                permission.triggerPermissionGranted.value = System.currentTimeMillis()
            }
        }
    private lateinit var permission: CheckPermission
    private var onSuccessFileLaunch: (File) -> Unit = {}

    @Composable
    abstract fun Content()

    fun onBackApplication() {
        onBackPressedDispatcher.onBackPressed()
    }

    fun filePicker(mimeTypes: Array<String>, callback: (File) -> Unit) {
        onSuccessFileLaunch = callback
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.type = "*/*"
        i.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        filePickerLauncher.launch(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permission = CheckPermission()
        setWindow()
        setContent {
            ISTAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }

    open fun setWindow() {
        WindowManagerCompact(this)
            .isTransparentStatusBar(true)
            .setStatusBarTheme(WindowManagerCompact.StatusBarTheme.DARK)
            .commit()
    }

    fun checkPermissions(
        permissions: Array<String>,
        onSuccess: () -> Unit = {},
    ) {
        permission
            .setLifecycleOwner(this)
            .setPermissionLauncher(permissionLauncher)
            .permission(permissions)
            .setOnPermissionGrantedListener {
                onSuccess()
            }

        permission.execute()
    }
}
