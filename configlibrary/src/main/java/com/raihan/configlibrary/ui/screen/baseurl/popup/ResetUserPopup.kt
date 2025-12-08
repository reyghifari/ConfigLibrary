package com.raihan.configlibrary.ui.screen.baseurl.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.raihan.configlibrary.ui.component.ButtonIST
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun ResetUserPopup(
    onDismissRequest: () -> Unit = {},
    onClickButton: () -> Unit = {},
) {
    Dialog(onDismissRequest) {
        Column(
            Modifier
                .background(Color.White)
                .padding(Dimens.x3)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Hapus User Data",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = Dimens.x3),
                text = "Proses ini akan menghapus semua data user kamu didalam handphone."
            )
            ButtonIST(
                modifier = Modifier
                    .padding(top = Dimens.x6),
                label = "Hapus",
                onClick = onClickButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResetUserPopupPreview() {
    ISTAppTheme {
        ResetUserPopup()
    }
}