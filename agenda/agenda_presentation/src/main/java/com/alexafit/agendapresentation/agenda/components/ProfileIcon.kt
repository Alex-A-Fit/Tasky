package com.alexafit.agendapresentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.MediumGray

@Composable
fun ProfileIcon() {
    val spacing = LocalSpacing.current

    /**
     * Just pasting progress for now. Need to create click listener for displaying dropdown
     */
    Text(
        text = "AA",
        modifier = Modifier
            .background(MediumGray, RoundedCornerShape(spacing.spaceLarge))
            .padding(spacing.spaceSmall),
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.End
    )
}
