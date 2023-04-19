package com.alexafit.tasky.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.alexafit.coreui.DarkGray2
import com.alexafit.coreui.MediumGray
import com.alexafit.coreui.TaskyGreen
import com.alexafit.coreui.Typography
import com.alexafit.coreui.White

private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = TaskyGreen,
    secondary = DarkGray2,
    secondaryVariant = MediumGray,
    surface = DarkGray2,
    background = White,
    onPrimary = DarkGray2,
    onSecondary = White,
    onSurface = White,
    onBackground = DarkGray2
)

private val LightColorPalette = lightColors(
    primary = DarkGray2,
    primaryVariant = TaskyGreen,
    secondary = White,
    secondaryVariant = MediumGray,
    surface = White,
    background = DarkGray2,
    onPrimary = White,
    onSecondary = DarkGray2,
    onSurface = DarkGray2,
    onBackground = White
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
