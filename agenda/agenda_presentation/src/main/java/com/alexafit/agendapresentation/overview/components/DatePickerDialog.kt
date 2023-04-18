package com.alexafit.agendapresentation.overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.alexafit.agendapresentation.R
import com.alexafit.agendapresentation.overview.model.OverviewState
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    overviewState: OverviewState,
    onDateSelectedClickEvent: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val dialog = android.app.DatePickerDialog(context)
    dialog.setOnDateSetListener { _, year, month, dayOfMonth ->
        val chosenDate: LocalDate = LocalDate.of(year, (month + 1), dayOfMonth)
        onDateSelectedClickEvent(chosenDate)
    }
    Row(
        modifier = Modifier
            .clickable {
                dialog.datePicker.updateDate(
                    overviewState.startingDate.year,
                    (overviewState.startingDate.month.value - 1),
                    overviewState.startingDate.dayOfMonth
                )
                dialog.show()
            }
            .wrapContentWidth(unbounded = false)
    ) {
        Text(
            text = overviewState.currentMonth,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
            contentDescription = stringResource(id = R.string.content_desc_dropdown_arrow),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
