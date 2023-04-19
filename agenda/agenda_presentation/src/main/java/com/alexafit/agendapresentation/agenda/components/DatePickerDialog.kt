package com.alexafit.agendapresentation.agenda.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import com.alexafit.agendapresentation.agenda.model.AgendaState
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    agendaState: AgendaState,
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
                    agendaState.startingDate.year,
                    (agendaState.startingDate.month.value - 1),
                    agendaState.startingDate.dayOfMonth
                )
                dialog.show()
            }
    ) {
        Text(
            text = agendaState.currentMonth,
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
