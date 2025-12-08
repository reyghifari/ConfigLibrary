package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabIST(
    modifier: Modifier = Modifier,
    datas: List<String> = listOf("Tab 1", "Tab 2"),
    selected: Int = 0,
    onSelected: (Int) -> Unit = {},
) {
    var lSelected by remember { mutableIntStateOf(selected) }
    PrimaryTabRow(
        modifier = modifier,
        selectedTabIndex = lSelected,
    ) {
        datas.forEachIndexed { i, s ->
            Tab(
                modifier = Modifier.height(Dimens.x12),
                selected = lSelected == i,
                onClick = {
                    lSelected = i
                    onSelected(i)
                }
            ) {
                Text(text = s)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabISTPreview() {
    ISTAppTheme {
        TabIST()
    }
}