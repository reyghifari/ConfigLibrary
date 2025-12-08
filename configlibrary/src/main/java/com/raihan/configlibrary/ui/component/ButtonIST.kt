package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.ui.theme.Amber200
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.Gray
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.raihan.configlibrary.ui.theme.Orange

@Composable
fun ButtonIST(
    modifier: Modifier = Modifier,
    label: String = "Button",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick,
	    modifier = modifier
            .height(Dimens.x12)
            .fillMaxWidth(),
	    colors = ButtonDefaults.buttonColors(
			containerColor = Orange,
			disabledContainerColor = Gray ,
			contentColor =Amber200,
			disabledContentColor = Gray,
		),
	    enabled = enabled
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonISTPreview() {
    ISTAppTheme {
        ButtonIST()
    }
}
