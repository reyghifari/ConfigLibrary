package com.raihan.configlibrary.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.raihan.configlibrary.core.model.menu.ModelMenu
import com.raihan.configlibrary.ui.component.AppBar
import com.raihan.configlibrary.ui.component.ItemList
import com.raihan.configlibrary.ui.screen.baseurl.BaseURLActivity
import com.raihan.configlibrary.ui.screen.exception.ExceptionActivity
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.raihan.configlibrary.ui.theme.White

class HomeActivity : BChucker() {

	companion object {
		private const val ID_ENVIRONMENT_SERVICES = "environment_services"
		private const val ID_EXCEPTIONS_LOG = "exception_log"
	}

	@Composable
	override fun Content() {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = Dimens.x4)
				.background(color = White)
		) {
			AppBar(
				title = "Application Config",
			)
			LazyColumn(
				Modifier
					.padding(top = Dimens.x3)
					.zIndex(-1f)
			) {
				itemsIndexed(listMenu()) { i, menu ->
					val top = if (i > 0 && menu.groupLabel != "") Dimens.container else 0.dp
					ItemList(
						modifier = Modifier.padding(top = top),
						title = menu.name,
						description = menu.description,
						groupLabel = menu.groupLabel,
						index = i,
						suffix = {
							Icon(
								painter = rememberVectorPainter(image = Icons.AutoMirrored.Rounded.KeyboardArrowRight),
								contentDescription = "image suffix",
							)
						},
						onClick = {
							when (menu.id) {
								ID_ENVIRONMENT_SERVICES -> BaseURLActivity::class.java.next()
								ID_EXCEPTIONS_LOG -> ExceptionActivity::class.java.next()
							}
						}
					)
				}
			}
		}
	}


	private fun listMenu() = listOf(
		ModelMenu(
			ID_ENVIRONMENT_SERVICES,
			"API Base URL",
			"Menu untuk merubah base url endpoint environment DEV atau UAT",
		),
		ModelMenu(
			ID_EXCEPTIONS_LOG,
			"Exceptions Log",
			"Menu untuk melihat list exceptions atau error pada aplikasi"
		)
	)

	@Preview(showBackground = true)
	@Composable
	fun ContentPreview() {
		ISTAppTheme {
			Content()
		}
	}
}
