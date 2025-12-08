package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.LightBlue400
import com.raihan.configlibrary.ui.theme.White

@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: String = "Lorem ipsum",
    noteAnnotated: AnnotatedString? = null,
) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.x2))
            .background(LightBlue400)
    ) {
        val (border, icon, label, text) = createRefs()
        val topGuideline = createGuidelineFromTop(Dimens.x2)
        val bottomGuideline = createGuidelineFromBottom(Dimens.x2)
        Box(
            Modifier.width(Dimens.x2)
                .background(White)
                .constrainAs(border) {
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Icon(
            Icons.Outlined.Info,
            "Icons Info",
            Modifier.constrainAs(icon) {
                top.linkTo(topGuideline, Dimens.x2)
                start.linkTo(border.end, Dimens.x3)
            }
        )
        Text(
            text = "NOTE",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(label) {
                start.linkTo(icon.end, Dimens.x3)
                top.linkTo(icon.top, Dimens.x)
                bottom.linkTo(icon.bottom, Dimens.x2)
            }
        )
        noteAnnotated.also { ta ->
            val m = Modifier.constrainAs(text) {
                width = Dimension.fillToConstraints
                start.linkTo(icon.start)
                end.linkTo(parent.end, Dimens.x5)
                top.linkTo(icon.bottom, Dimens.x2)
                bottom.linkTo(bottomGuideline, Dimens.x2)
            }
            when (ta) {
                null -> Text(note, m)
                else -> Text(ta, m)
            }
        }
    }
}
