package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raihan.configlibrary.core.model.dropdown.ModelDropdown
import com.raihan.configlibrary.ui.theme.Black
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.Gray300
import com.raihan.configlibrary.ui.theme.Gray400
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun Dropdown(
    modifier: Modifier = Modifier,
    label: String = "Label",
    hint: String = "Hint",
    datas: List<ModelDropdown> = listOf(),
    selected: String = "",
    onSelected: (ModelDropdown) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(selected) }
    var inputName by remember { mutableStateOf(hint) }
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
    Column(
        modifier = modifier.wrapContentSize()
    ) {
        Text(text = label, fontSize = 14.sp)
        ConstraintLayout(
            Modifier.height(Dimens.x16)
                .fillMaxWidth()
                .padding(vertical = Dimens.x2)
                .border(1.dp, Gray300, RoundedCornerShape(Dimens.x2))
                .clickable { expanded = true },
        ) {
            val (text, suffix) = createRefs()
            Text(
                text = inputName,
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(text) {
                        width = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, Dimens.x3)
                        end.linkTo(suffix.start, Dimens.x2)
                    },
                maxLines = 1,
                color = when (value.isEmpty()) {
                    true -> Gray400
                    else -> Black
                },
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                Icons.Rounded.KeyboardArrowDown,
                "image suffix",
                Modifier.constrainAs(suffix) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, Dimens.x3)
                }
            )
        }
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
                                contentDescription = "suffix dropdown"
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
fun DropdownPreview() {
    ISTAppTheme {
        Dropdown()
    }
}