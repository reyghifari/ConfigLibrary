package com.raihan.configlibrary.ui.component.codeeditor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun JsonEditor(
    text: String = "",
    onTextChange: (String) -> Unit = {},
) {
    var isFromTextChange by remember { mutableStateOf(false) }
    AndroidView(
        factory = {
            CodeEditorView(it).apply {
                setStyleJSON()
                setOnTextChangeListener { code ->
                    isFromTextChange = true
                    onTextChange(code)
                }
            }
        },
        update = {
            if (isFromTextChange.not()) it.setText(text)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun JsonEditorPreview() {
    ISTAppTheme {
        JsonEditor()
    }
}