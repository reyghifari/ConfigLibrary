package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.core.extensions.harmoniousColor
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun IconAlias(
    alias: String = "IST",
    index: Int = 0,
) {
    val hColor = index.harmoniousColor()
    val aliasValidLength = alias.ifEmpty { "-" }
    val newAlias = when (aliasValidLength.length > 2) {
        true -> aliasValidLength.substring(0, 2)
        else -> aliasValidLength
    }
    Column(
        modifier = Modifier.size(Dimens.x12)
            .clip(CircleShape)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(newAlias, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun IconPreview() {
    ISTAppTheme {
        IconAlias()
    }
}