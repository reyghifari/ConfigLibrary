package com.raihan.configlibrary.ui.screen.exception.page.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihan.configlibrary.ui.component.AppBar
import com.raihan.configlibrary.ui.component.codeeditor.KotlinEditor
import com.raihan.configlibrary.ui.screen.exception.ExceptionProvider
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import co.id.pegadaian.tring.core.theme.TringColor
import co.id.pegadaian.tring.core.theme.TringDp
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.White

@Composable
fun DetailPage() {
	ExceptionProvider {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = Dimens.x4)
				.background(color = White)
		) {
			AppBar(
				title = "Exceptions Detail",
				prefixIcon = Icons.AutoMirrored.Rounded.ArrowBack,
				onClickPrefix = { navigation?.popBackStack() },
			)
			Column(
				Modifier
					.fillMaxSize(),
			) {
				KotlinEditor(vm.selected.message, false)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
	ISTAppTheme {
		DetailPage()
	}
}
