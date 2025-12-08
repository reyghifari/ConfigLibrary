package com.raihan.configlibrary.ui.screen.home

import android.content.Intent
import androidx.activity.viewModels
import com.raihan.configlibrary.core.base.BaseActivity

abstract class ACore : BaseActivity() {
    protected val homeVM: HomeViewModel by viewModels()

    protected fun Class<*>.next() {
        startActivity(Intent(this@ACore, this))
    }
}