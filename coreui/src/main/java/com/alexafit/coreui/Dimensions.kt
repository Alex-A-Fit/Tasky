package com.alexafit.coreui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val cornerShapeSmall: Dp = 2.dp,
    val cornerShapeMedium: Dp = 5.dp,
    val cornerShapeLarge: Dp = 10.dp,
    val cornerShapeExtraLarge: Dp = 100.dp,
    val shadowSmall: Dp = 2.dp,
    val shadowMedium: Dp = 5.dp,
    val spaceSmallest: Dp = 2.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
