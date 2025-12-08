package com.raihan.configlibrary.ui.screen.baseurl

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raihan.configlibrary.core.base.BaseActivity
import com.raihan.configlibrary.core.storage.SetupPref
import com.raihan.configlibrary.ui.component.AppBar
import com.raihan.configlibrary.ui.component.ButtonIST
import com.raihan.configlibrary.ui.component.Dropdown
import com.raihan.configlibrary.ui.component.Note
import com.raihan.configlibrary.ui.screen.baseurl.popup.ResetUserPopup
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import com.raihan.configlibrary.ui.theme.White

class BaseURLActivity : BaseActivity() {

    @Composable
    override fun Content() {

        val vm: BaseURLViewModel = viewModel()

        var info by remember { mutableStateOf(AnnotatedString("")) }
        var baseURL by remember { mutableStateOf("null") }
        var isOpenPopupResetUser by remember { mutableStateOf(false) }
        val context: Context = this
        LaunchedEffect(true) {
            baseURL = vm.get(context)
            info = vm.setInfo(baseURL)
        }
        Column(
	        modifier = Modifier
		        .fillMaxSize()
		        .padding(top = Dimens.x4)
		        .background(color = White)
		) {
            AppBar(
                title = "API Base URL",
                prefixIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                onClickPrefix = {
                    onBackApplication()
                }
            )
            ConstraintLayout(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val (dropdown, note, button) = createRefs()
                val startGuideline = createGuidelineFromStart(Dimens.container)
                val endGuideline = createGuidelineFromEnd(Dimens.container)
                Dropdown(
                    label = "Environment",
                    hint = "Environment",
                    datas = vm.listENV(context),
                    selected = baseURL,
                    onSelected = {
                        baseURL = it.value
                        info = vm.setInfo(baseURL)
                    },
                    modifier = Modifier
                        .constrainAs(dropdown) {
                            start.linkTo(startGuideline)
                            end.linkTo(endGuideline)
                            top.linkTo(parent.top, Dimens.x10)
                            width = Dimension.fillToConstraints
                        },
                )
                Note(
                    note = "Default",
                    noteAnnotated = info,
                    modifier = Modifier
                        .padding(horizontal = Dimens.container)
                        .constrainAs(note) {
                            start.linkTo(startGuideline)
                            end.linkTo(endGuideline)
                            top.linkTo(dropdown.bottom, Dimens.container)
                        },
                )
                ButtonIST(
                    label = "Simpan",
                    onClick = {
                        isOpenPopupResetUser = true
                    },
                    modifier = Modifier.constrainAs(button) {
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideline)
                        linkTo(
                            note.bottom,
                            parent.bottom,
                            Dimens.container,
                            Dimens.container,
                            bias = 1F
                        )
                        width = Dimension.fillToConstraints
                    }
                )
            }
            if (isOpenPopupResetUser) {
                ResetUserPopup(
                    onDismissRequest = {
                        isOpenPopupResetUser = false
                    },
                    onClickButton = {
                        isOpenPopupResetUser = false
                        vm.save(context, baseURL)
                        nextPage()
                    }
                )
            }
        }
    }

    private fun nextPage() {
        val i = Intent().setClassName(
            this,
            SetupPref(this).get().resetFinishDestination
        )
        i.flags =
            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        ISTAppTheme {
            Content()
        }
    }
}
