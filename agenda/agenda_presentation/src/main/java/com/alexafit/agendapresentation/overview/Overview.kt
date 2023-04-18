package com.alexafit.agendapresentation.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alexafit.agendapresentation.overview.components.DatePickerDialog
import com.alexafit.agendapresentation.overview.components.ProfileIcon
import com.alexafit.coreui.LocalSpacing

@Composable
fun Overview() {
    /**
     * Stopped here for now
     */
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceExtraSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DatePickerDialog()
        ProfileIcon()
    }

}
