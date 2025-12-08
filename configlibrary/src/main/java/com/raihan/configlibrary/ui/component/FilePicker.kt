package com.raihan.configlibrary.ui.component

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raihan.configlibrary.R
import com.raihan.configlibrary.core.extensions.base64
import com.raihan.configlibrary.core.extensions.getBaseActivity
import com.raihan.configlibrary.core.model.MimeType
import com.raihan.configlibrary.core.model.mockservices.ModelOnSuccess
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.Gray300
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun FilePicker(
    modifier: Modifier = Modifier,
    fileName: String = "",
    fileBase64: String = "",
    mimeType: MimeType = MimeType.PDF,
    onSuccess: (ModelOnSuccess) -> Unit = {},
) {
    var lFileName by remember { mutableStateOf(fileName) }
    var lFileBase64 by remember { mutableStateOf(fileBase64) }
    val baseActivity = LocalContext.current.getBaseActivity()

    ConstraintLayout(
        modifier
            .defaultMinSize(minHeight = Dimens.x13)
            .fillMaxWidth()
            .border(1.dp, Gray300, RoundedCornerShape(Dimens.x2))
            .clickable {
                baseActivity
                    ?.apply {
                        checkPermissions(
                            arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            )
                        ) {
                            filePicker(arrayOf(mimeType.value)) { file ->
                                if (file.length() < Int.MAX_VALUE) {
                                    lFileName = file.name
                                    lFileBase64 = file.base64()
                                    onSuccess(ModelOnSuccess(file, lFileName, lFileBase64))
                                }
                            }
                        }
                    }
            }
    ) {
        val (image, text) = createRefs()
        val topGuideline = createGuidelineFromTop(Dimens.x3)
        val bottomGuideline = createGuidelineFromBottom(Dimens.x3)
        val startGuideline = createGuidelineFromStart(Dimens.container)
        val endGuideline = createGuidelineFromEnd(Dimens.container)

        Image(
            painter = when (lFileName == "" && lFileBase64 == "") {
                true -> rememberVectorPainter(image = Icons.Rounded.Add)
                else -> painterResource(id = R.drawable.ic_pdf)
            },
            contentDescription = "icon",
            contentScale = ContentScale.Inside,
            modifier = Modifier.size(Dimens.x12)
                .constrainAs(image) {
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomGuideline)
                    start.linkTo(startGuideline)
                }
        )
        Text(
            text = lFileName.ifEmpty { "Pilih File" },
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(topGuideline)
                    start.linkTo(image.end, Dimens.x3)
                    bottom.linkTo(bottomGuideline)
                    end.linkTo(endGuideline)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilePickerPreview() {
    ISTAppTheme {
        FilePicker()
    }
}