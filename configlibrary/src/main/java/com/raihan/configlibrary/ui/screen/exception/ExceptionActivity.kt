package com.raihan.configlibrary.ui.screen.exception

import android.app.Activity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.base.BaseActivity
import com.raihan.configlibrary.core.extensions.saveText
import com.raihan.configlibrary.core.extensions.shareHTML
import com.raihan.configlibrary.ui.screen.exception.page.detail.DetailPage
import com.raihan.configlibrary.ui.screen.exception.page.home.HomePage
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.google.gson.Gson
import com.raihan.configlibrary.R

class ExceptionActivity : BaseActivity(), ExceptionInterface {

    override val vm: ExceptionViewModel by viewModels()
    override var navigation: NavHostController? = null
    override val baseActivity: BaseActivity get() = this
    override val activity: Activity get() = this

    @Composable
    override fun Content() {
        navigation = rememberNavController()
        NavHost(
            navController = navigation!!,
            startDestination = Constants.EXCEPTIONS.PAGE_HOME
        ) {
            composable(Constants.EXCEPTIONS.PAGE_HOME) { HomePage() }
            composable(Constants.EXCEPTIONS.PAGE_DETAIL) { DetailPage() }
        }
    }

    override fun export(start: Long, end: Long) {
        vm.getExceptionBaseOnDate(activity, start, end) { datas ->
            if (datas.isNotEmpty()) {
                val exception = Gson().toJson(datas)
                val replaceKey = "AHSKJFA123KHJKLAHD98987"
                val res = resources
                    .openRawResource(R.raw.exception_template)
                    .bufferedReader()
                    .use { it.readText() }
                val newException = exception
                    .replace("\\n", "\\\\n")
                    .replace("\\t", "\\\\t")
                val log = res.replace(replaceKey, newException)
                val loc = saveText(log, "Exception_${System.currentTimeMillis()}.html")
                shareHTML(loc)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        ISTAppTheme {
            Content()
        }
    }
}