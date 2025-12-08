package com.raihan.configlibrary.core.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.core.util.WindowManagerCompact
import com.raihan.configlibrary.ui.theme.ISTAppTheme

abstract class BaseActivityProcess : ComponentActivity() {

    companion object {
        const val INTENT_PROCESS = "intent_process"
        const val PROCESS_RESET_USER_DATA = "reset_user"
    }

    abstract fun onResetUserData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindow()
        setContent {
            Content()
        }
        process()
    }

    private fun process() {
        val i = intent.getStringExtra(INTENT_PROCESS) ?: ""
        when (i) {
            PROCESS_RESET_USER_DATA -> onResetUserData()
        }
    }

    private fun setWindow() {
        WindowManagerCompact(this)
            .isTransparentStatusBar(true)
            .setStatusBarTheme(WindowManagerCompact.StatusBarTheme.DARK)
            .commit()
    }
}

@Composable
fun Content() {
    ISTAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Processing ...")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content()
}
