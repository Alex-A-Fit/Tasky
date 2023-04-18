package com.alexafit.agendapresentation.overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.Orange

@Composable
fun OverviewCalendarDate(
    isSelected: Boolean,
    dayAcronym: String,
    dayOfMonth: String,
    onDateSelectedClickEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = if (isSelected) {
            modifier
                .background(Orange, RoundedCornerShape(percent = 50))
                .clickable { onDateSelectedClickEvent() }
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium)
        } else {
            modifier.clickable { onDateSelectedClickEvent() }
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dayAcronym,
            color = if (isSelected) {
                MaterialTheme.colors.onSurface
            } else {
                MaterialTheme.colors.secondaryVariant
            },
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center
        )
        Text(
            text = dayOfMonth,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
    }
}
