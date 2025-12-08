package com.raihan.configlibrary.ui.screen.exception

import android.app.Activity
import androidx.navigation.NavHostController
import com.raihan.configlibrary.core.base.BaseActivity

interface ExceptionInterface {
    val vm: ExceptionViewModel
    val navigation: NavHostController?
    val baseActivity: BaseActivity
    val activity: Activity

    fun export(start: Long, end: Long)
}