package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.core.model.dropdown.ModelDropdown
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun DropdownText(
    label: String = "text",
    selected: String = "",
    datas: List<ModelDropdown> = listOf(),
    onSelected: (ModelDropdown) -> Unit = {},
) {
    var value by remember { mutableStateOf(selected) }
    var expanded by remember { mutableStateOf(false) }
    var inputName by remember { mutableStateOf(label) }
    if (selected.isNotEmpty()) {
        val dataFiltered = datas.filter { it.value == selected }
        if (dataFiltered.isNotEmpty()) {
            inputName = dataFiltered[0].label
            onSelected(dataFiltered[0])
        }
    }
    LaunchedEffect(selected) {
        value = selected
    }
    Column {
        ButtonText(
            label = inputName,
            suffix = Icons.Rounded.KeyboardArrowDown,
            onClick = { expanded = true }
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            datas.forEach {
                DropdownMenuItem(
                    text = { Text(it.label) },
                    onClick = {
                        expanded = false
                        value = it.value
                        inputName = it.label
                        onSelected(it)
                    },
                    trailingIcon = {
                        if (value == it.value) {
                            Icon(
                                Icons.Rounded.Check,
                                contentDescription = "suffix dropdown",
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownTextPreview() {
    ISTAppTheme {
        DropdownText()
    }
}
