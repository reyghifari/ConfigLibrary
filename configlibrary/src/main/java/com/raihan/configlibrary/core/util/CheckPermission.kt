package com.raihan.configlibrary.core.util

import android.Manifest
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class CheckPermission {

    private var isGrantedPermission: Boolean = false
    private var permission: String = ""
    private var listPermission: MutableList<String> = mutableListOf()
    private var indexExecute: Int = -1
    private var onPermissionGranted: () -> Unit = {}

    val triggerPermissionGranted = MutableLiveData(0L)
    private var launcher: ActivityResultLauncher<String>? = null

    fun permission(lP: Array<String>): CheckPermission {
        listPermission = mutableListOf()
        permission = ""
        indexExecute = -1
        lP.forEach {
            if (it.isConditionalPermission().not()) listPermission.add(it)
        }
        return this
    }

    fun setPermissionLauncher(l: ActivityResultLauncher<String>): CheckPermission {
        launcher = l
        return this
    }

    fun setLifecycleOwner(owner: LifecycleOwner): CheckPermission {
        triggerPermissionGranted.observe(owner) {
            if (it > 0) checkPermission()
        }
        return this
    }

    fun setOnPermissionGrantedListener(callback: () -> Unit) {
        onPermissionGranted = callback
    }

    fun execute() {
        if (listPermission.isNotEmpty()) {
            checkPermission()
        } else {
            onPermissionGranted()
        }
    }

    private fun checkPermission() {
        indexExecute++
        if (indexExecute < listPermission.size) {
            permission = listPermission[indexExecute]
            launcher?.launch(permission)
        } else {
            isGrantedPermission = true
            onPermissionGranted()
        }
    }

    private fun String.isConditionalPermission(): Boolean {
        return when (this) {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            -> Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

            Manifest.permission.POST_NOTIFICATIONS -> Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            Manifest.permission.BLUETOOTH_CONNECT -> Build.VERSION.SDK_INT < Build.VERSION_CODES.S
            else -> false
        }
    }
}