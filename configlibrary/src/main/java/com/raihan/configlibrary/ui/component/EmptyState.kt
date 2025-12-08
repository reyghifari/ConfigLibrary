package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raihan.configlibrary.R

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    title: String = "Kosong",
    description: String = "Tidak ada data tersimpan",
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))
    Column(
        modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            iterations = Int.MAX_VALUE
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = modifier.padding(top = Dimens.x5),
            text = description,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyStatePreview() {
    ISTAppTheme {
        EmptyState()
    }
}