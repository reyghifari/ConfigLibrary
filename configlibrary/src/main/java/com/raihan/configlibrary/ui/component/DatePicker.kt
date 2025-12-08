package com.raihan.configlibrary.ui.component

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raihan.configlibrary.ui.theme.Black
import com.raihan.configlibrary.ui.theme.Dimens
import com.raihan.configlibrary.ui.theme.Gray300
import com.raihan.configlibrary.ui.theme.Gray400
import com.raihan.configlibrary.ui.theme.ISTAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    label: String = "Label",
    hint: String = "Hint",
    value: Calendar? = null,
    positiveButtonText: String = "Simpan",
    setOnDateSetListener: (Calendar) -> Unit = {},
) {
    var lValue by remember { mutableStateOf(value) }
    var inputName by remember { mutableStateOf(hint) }
    val context: Context = LocalContext.current
    Column(
        modifier = modifier.wrapContentSize()
    ) {
        Text(text = label, fontSize = 14.sp)
        Row(
            Modifier.height(Dimens.x16)
                .fillMaxWidth()
                .padding(vertical = Dimens.x2)
                .border(1.dp, Gray300, RoundedCornerShape(Dimens.x2))
                .clickable {
                    val c = value ?: Calendar.getInstance()
                    context.showDatePicker(c, positiveButtonText) {
                        lValue = it
                        inputName =
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it.time)
                        setOnDateSetListener(it)
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.x3),
                text = inputName,
                fontSize = 16.sp,
                maxLines = 1,
                color = when (lValue) {
                    null -> Gray400
                    else -> Black
                },
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun Context.showDatePicker(
    cal: Calendar = Calendar.getInstance(),
    positiveButtonText: String = "Simpan",
    setOnDateSetListener: (Calendar) -> Unit,
) {
    val date = DatePickerDialog(this)
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
    date.updateDate(year, month, dayOfMonth)
    date.setOnDateSetListener { _, y, m, d ->
        val lCal = Calendar.getInstance()
        lCal.set(Calendar.YEAR, y)
        lCal.set(Calendar.MONTH, m)
        lCal.set(Calendar.DAY_OF_MONTH, d)
        lCal.timeInMillis
        setOnDateSetListener(lCal)
    }
    date.show()
    date.getButton(DatePickerDialog.BUTTON_POSITIVE)?.text = positiveButtonText
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {
    ISTAppTheme {
        DatePicker()
    }
}