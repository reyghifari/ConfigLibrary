package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldIST(
    modifier: Modifier = Modifier,
    label: String = "Label",
    placeholder: String = "Placeholder",
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var textState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val text = textState.copy(text = value)
    TextField(
        modifier = modifier.wrapContentHeight(),
        value = text,
        onValueChange = {
            textState = it
            onValueChange(it.text)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldISTPreview() {
    ISTAppTheme {
        TextFieldIST()
    }
}