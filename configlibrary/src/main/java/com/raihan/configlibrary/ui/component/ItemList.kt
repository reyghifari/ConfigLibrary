package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.raihan.configlibrary.core.extensions.getFirstLater
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.Gray300
import com.raihan.configlibrary.ui.theme.Gray400
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ItemList(
	modifier: Modifier = Modifier,
	title: String = "Item Title",
	description: String = "Item Description",
	overline: @Composable (() -> Unit)? = null,
	groupLabel: String = "",
	index: Int = 0,
	suffix: @Composable (() -> Unit)? = null,
	onClick: () -> Unit = {},
	onLongClick: () -> Unit = {},
) {
	Column(modifier) {
		if (groupLabel.isNotEmpty()) {
			Text(
				text = groupLabel,
				modifier = Modifier.padding(start = Dimens.x3, bottom = Dimens.x2),
				fontSize = 12.sp,
				color = Gray400,
				fontWeight = FontWeight.Bold
			)
		}
		ListItem(
			modifier = Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick),
			headlineContent = { Text(title) },
			supportingContent = { Text(description) },
			overlineContent = overline,
			leadingContent = { IconAlias(title.getFirstLater(), index) },
			trailingContent = suffix,
		)
		HorizontalDivider(color = Gray300)
	}
}

@Preview(showBackground = true)
@Composable
fun ItemListPreview() {
	ISTAppTheme {
		ItemList()
	}
}
