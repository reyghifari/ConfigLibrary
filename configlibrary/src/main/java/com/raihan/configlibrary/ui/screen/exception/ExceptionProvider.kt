package com.raihan.configlibrary.ui.screen.exception

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.NavHostController
import com.raihan.configlibrary.core.base.BaseActivity

@Composable
fun ExceptionProvider(
    content: @Composable ExceptionInterface.() -> Unit,
) {
    val context = LocalContext.current
    if (context !is ExceptionActivity) {
        if (LocalInspectionMode.current) {
            val tempObj = object : ExceptionInterface {
                override val vm: ExceptionViewModel = ExceptionViewModel.Companion.preview()
                override val navigation: NavHostController? = null
                override val baseActivity: BaseActivity = object : BaseActivity() {
                    @Composable
                    override fun Content() {
                    }
                }
                override val activity: Activity = Activity()
                override fun export(start: Long, end: Long) {}
            }
            content(tempObj)
        } else {
            throw IllegalAccessError("Error casting context as MockServicesActivity")
        }
    } else {
        content(context)
    }
}