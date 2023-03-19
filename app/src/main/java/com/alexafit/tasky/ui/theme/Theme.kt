package com.alexafit.tasky.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.alexafit.coreui.DarkGray2
import com.alexafit.coreui.LightBlue
import com.alexafit.coreui.LightGray
import com.alexafit.coreui.MediumGray
import com.alexafit.coreui.SuccessGreen
import com.alexafit.coreui.TaskyGreen
import com.alexafit.coreui.Typography
import com.alexafit.coreui.White

private val DarkColorPalette = darkColors(
    primary = TaskyGreen,
    primaryVariant = SuccessGreen,
    secondary = LightGray,
    secondaryVariant = MediumGray,
    background = DarkGray2,
    surface = White,
    onSecondary = LightBlue
)

private val LightColorPalette = lightColors(
    primary = TaskyGreen,
    primaryVariant = SuccessGreen,
    secondary = LightGray,
    secondaryVariant = MediumGray,
    background = White,
    surface = DarkGray2,
    onSecondary = LightBlue
)

@Composable
fun TaskyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
