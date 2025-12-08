package com.raihan.configlibrary.ui.screen.exception.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.extensions.add
import com.raihan.configlibrary.ui.component.AppBar
import com.raihan.configlibrary.ui.component.ButtonText
import com.raihan.configlibrary.ui.component.DropdownText
import com.raihan.configlibrary.ui.component.EmptyState
import com.raihan.configlibrary.ui.component.ItemList
import com.raihan.configlibrary.ui.screen.exception.ExceptionProvider
import com.raihan.configlibrary.ui.screen.exception.popup.SelectDatePopup
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.raihan.configlibrary.core.extensions.remove
import com.raihan.configlibrary.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomePage() {
	var listSelected by remember { mutableStateOf(listOf<Long>()) }
	var isRemoveData by remember { mutableStateOf(false) }
	var isShowPopup by remember { mutableStateOf(false) }
	val context = LocalContext.current
	ExceptionProvider {
		vm.updateException(context)
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = Dimens.x4)
				.background(color = White)
		) {
			AppBar(
				title = "Exceptions Log",
				prefixIcon = when (isRemoveData) {
					false -> Icons.AutoMirrored.Rounded.ArrowBack
					else -> Icons.Rounded.Close
				},
				onClickPrefix = {
					if (isRemoveData) {
						listSelected = listOf()
						isRemoveData = false
					} else {
						baseActivity.onBackApplication()
					}
				},
				suffix = {
					when (isRemoveData) {
						false -> {
							if (vm.stateListException.isNotEmpty()) {
								IconButton(
									onClick = { isShowPopup = true },
									content = {
										Icon(
											painter = rememberVectorPainter(image = Icons.Rounded.Share),
											contentDescription = "image suffix"
										)
									}
								)
							}
						}

						else -> ButtonText("Hapus") {
							vm.deleteExceptionLog(activity, listSelected)
							isRemoveData = false
							listSelected = listOf()
						}
					}
				}
			)
			Column(
				Modifier
					.padding(top = Dimens.x3)
					.fillMaxSize(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Column(
					Modifier.fillMaxWidth(),
					horizontalAlignment = Alignment.End,
				) {
					DropdownText(
						selected = vm.exceptionType,
						datas = vm.exceptionTypeList(context),
						onSelected = { vm.updateException(context, it.value) }
					)
				}
				when (vm.stateListException.isNotEmpty()) {
					true ->
						LazyColumn(
							Modifier
								.fillMaxSize()
								.zIndex(-1f)
						) {
							itemsIndexed(vm.stateListException) { i, d ->
								ItemList(
									title = StringBuilder()
										.append(d.fileName)
										.append(":")
										.append(d.lineNumber)
										.toString(),
									description = d.className,
									overline = {
										val exDate = SimpleDateFormat(
											"HH:mm:ss dd MMMM yyyy",
											Locale.Builder().setLanguage("id").setRegion("ID").build()
										).format(d.dateMillis)
										Text(exDate)
									},
									index = i,
									suffix = {
										if (isRemoveData) {
											Checkbox(
												checked = listSelected.contains(d.id),
												onCheckedChange = {
													listSelected = when (it) {
														true -> listSelected.add(d.id)
														else -> listSelected.remove(d.id)
													}
												}
											)
										} else {
											Icon(
												painter = rememberVectorPainter(image = Icons.AutoMirrored.Rounded.KeyboardArrowRight),
												contentDescription = "image suffix"
											)
										}
									},
									onClick = {
										if (isRemoveData) {
											listSelected = when (listSelected.contains(d.id)) {
												true -> listSelected.remove(d.id)
												else -> listSelected.add(d.id)
											}
										} else {
											vm.selected = d
											navigation?.navigate(Constants.EXCEPTIONS.PAGE_DETAIL)
										}
									},
									onLongClick = {
										if (isRemoveData.not()) {
											listSelected = listSelected.add(d.id)
											isRemoveData = true
										}
									}
								)
							}
						}

					else -> EmptyState()
				}
			}
		}
		if (isShowPopup) {
			SelectDatePopup(
				onDismissRequest = { isShowPopup = false },
				setOnSubmit = { start, end ->
					export(start.timeInMillis, end.timeInMillis)
				}
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
	ISTAppTheme {
		HomePage()
	}
}
