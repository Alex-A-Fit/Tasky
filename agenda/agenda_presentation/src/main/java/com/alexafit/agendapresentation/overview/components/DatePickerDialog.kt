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
import java.time.LocalDate

@Composable
fun DatePickerDialog() {
    val context = LocalContext.current
    val dialog = android.app.DatePickerDialog(context)

    /**
     * Need to pass fn to save date selection
     */
    Row(
        modifier = Modifier
            .clickable { dialog.show() }
            .wrapContentWidth(unbounded = false)
    ) {
        Text(
            text = LocalDate.now().month.name,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
            contentDescription = stringResource(id = R.string.content_desc_dropdown_arrow),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
