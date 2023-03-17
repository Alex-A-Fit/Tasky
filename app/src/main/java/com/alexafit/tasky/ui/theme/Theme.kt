package com.alexafit.tasky.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.alexafit.core_ui.DarkGray2
import com.alexafit.core_ui.LightBlue
import com.alexafit.core_ui.SuccessGreen
import com.alexafit.core_ui.TaskyGreen
import com.alexafit.core_ui.Typography
import com.alexafit.core_ui.White

private val DarkColorPalette = darkColors(
    primary = TaskyGreen,
    primaryVariant = SuccessGreen,
    secondary = LightBlue,
    background = DarkGray2,
    surface = White
)

private val LightColorPalette = lightColors(
    primary = TaskyGreen,
    primaryVariant = SuccessGreen,
    secondary = LightBlue,
    background = White,
    surface = DarkGray2
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