package com.raihan.configlibrary.core.util

import android.R
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class WindowManagerCompact(private val activity: Activity) {
    enum class InputMode { ADJUST_PAN, ADJUST_RESIZE }
    enum class StatusBarTheme { LIGHT, DARK }

    private var inputMode: InputMode = InputMode.ADJUST_PAN
    private var isTransparentStatusBar: Boolean = false
    private var statusBarTheme: StatusBarTheme? = null

    private var isSetupSoftInputKeyboard: Boolean = false
    private var isSetupStatusBarTheme: Boolean = false

    fun setInputMode(mode: InputMode): WindowManagerCompact {
        inputMode = mode
        isSetupSoftInputKeyboard = true
        return this
    }

    fun isTransparentStatusBar(isTransparent: Boolean): WindowManagerCompact {
        isTransparentStatusBar = isTransparent
        isSetupSoftInputKeyboard = true
        return this
    }

    fun setStatusBarTheme(theme: StatusBarTheme): WindowManagerCompact {
        statusBarTheme = theme
        isSetupStatusBarTheme = true
        return this
    }

    fun commit() = with(activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (isSetupSoftInputKeyboard) setSoftInputModeAPI30(getContentView())
            if (isSetupStatusBarTheme) setStatusBarThemeAPI30()

            if (isTransparentStatusBar) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = statusBarTheme == StatusBarTheme.DARK
                    show(WindowInsetsCompat.Type.statusBars())
                }

                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    try {
                        val method = window.javaClass.getMethod("setStatusBarContrastEnforced", Boolean::class.java)
                        method.invoke(window, false)
                    } catch (e: Exception) {
                        // Ignore if the method doesn't exist or fails
                    }
                }

                @Suppress("DEPRECATION")
                window.statusBarColor = Color.TRANSPARENT
            }
        } else {
            setupStatusBarTheme()
            if (isSetupSoftInputKeyboard) setSoftInputMode(getContentView())
        }
        isSetupSoftInputKeyboard = false
        isSetupStatusBarTheme = false
    }

    private fun getContentView() = activity.findViewById<ViewGroup>(R.id.content)

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setStatusBarThemeAPI30() = with(activity) {
        val sTheme = when (statusBarTheme) {
            StatusBarTheme.LIGHT -> 0
            StatusBarTheme.DARK -> WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            else -> null
        }
        if (sTheme != null) {
            window.insetsController?.setSystemBarsAppearance(
                sTheme,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }

    private fun setupStatusBarTheme() = with(activity) {
        if (isTransparentStatusBar) {

            WindowCompat.setDecorFitsSystemWindows(window, false)

            val controller = WindowInsetsControllerCompat(window, window.decorView)

            when (statusBarTheme) {
                StatusBarTheme.LIGHT -> controller.isAppearanceLightStatusBars = false
                StatusBarTheme.DARK -> controller.isAppearanceLightStatusBars = true
                else -> { /* Do nothing */ }
            }

            controller.show(WindowInsetsCompat.Type.statusBars())

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            @Suppress("DEPRECATION")
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setSoftInputMode(rootView: ViewGroup) = with(activity) {
        when (inputMode) {
            InputMode.ADJUST_PAN -> window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            InputMode.ADJUST_RESIZE -> {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
                    setAdjustResize(windowInsets, view)
                    WindowInsetsCompat.CONSUMED
                }
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
            }
        }
    }

    private fun setSoftInputModeAPI30(rootView: ViewGroup) = with(activity) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
            when (inputMode) {
                InputMode.ADJUST_PAN -> setAdjustPanAPI30(windowInsets, view)
                InputMode.ADJUST_RESIZE -> setAdjustResize(windowInsets, view)
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setAdjustPanAPI30(windowInsets: WindowInsetsCompat, view: View) = with(activity) {
        val navbar = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        val status = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
        val pTop = if (isTransparentStatusBar.not()) status.top else 0
        view.setPadding(0, pTop, 0, navbar.bottom)
    }

    private fun setAdjustResize(windowInsets: WindowInsetsCompat, view: View) =
        with(activity) {
            val imeHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            val navbar = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val status = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            val pTop = if (isTransparentStatusBar.not()) status.top else 0
            val pBottom = if (imeHeight == 0) navbar.bottom else imeHeight
            view.setPadding(0, pTop, 0, pBottom)
        }
}
