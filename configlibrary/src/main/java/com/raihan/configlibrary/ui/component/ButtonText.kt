package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.raihan.configlibrary.ui.theme.LightBlue400


@Composable
fun ButtonText(
    label: String = "Button",
    suffix: ImageVector? = null,
    onClick: () -> Unit = {},
) {
    TextButton(
        onClick,
        Modifier.height(Dimens.x12)
    ) {
        Text(label, color = LightBlue400)
        if (suffix != null) {
            Image(
                painter = rememberVectorPainter(image = suffix),
                colorFilter = ColorFilter.tint(LightBlue400),
                contentDescription = "Image"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonTextPreview() {
    ISTAppTheme {
        ButtonText()
    }
}
