package com.alexafit.agendapresentation.agenda.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import com.alexafit.coreui.LightGray

data class AgendaItemOverview(
    val title: String,
    val subtitle: String,
    val date: String,
    val cardColor: Color = LightGray,
    val contrastingColor: Color = determineContrastingColor(cardColor)
)

fun determineContrastingColor(baseColor: Color): Color {
    return if (
        ColorUtils.calculateContrast((Color.White).toArgb(), baseColor.toArgb())
    >
        ColorUtils.calculateContrast((Color.Black).toArgb(), baseColor.toArgb())
    ) {
        Color.White // use white text
    } else {
        Color.Black // use black text
    }
}
