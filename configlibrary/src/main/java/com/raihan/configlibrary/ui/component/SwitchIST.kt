package com.raihan.configlibrary.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.ISTAppTheme

@Composable
fun SwitchIST(
    label: String = "Label",
    checked: Boolean = false,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    var lChecked by remember { mutableStateOf(checked) }
    ConstraintLayout(
        modifier
            .height(Dimens.x12)
            .fillMaxWidth()
    ) {
        val (text, switch) = createRefs()
        Text(
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(switch.start, Dimens.x3)
                width = Dimension.fillToConstraints
            }
        )
        Switch(
            checked = lChecked,
            onCheckedChange = {
                lChecked = it
                onCheckedChange(it)
            },
            modifier = Modifier.constrainAs(switch) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SwitchISTPreview() {
    ISTAppTheme {
        SwitchIST()
    }
}