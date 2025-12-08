package com.raihan.configlibrary.core.extensions

import com.raihan.configlibrary.ui.theme.metrixHarmoniousColor

fun Int.harmoniousColor() = metrixHarmoniousColor[this % metrixHarmoniousColor.size]