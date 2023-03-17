package com.alexafit.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Inter {
    val bold: FontFamily =
        FontFamily(
            Font(R.font.inter_regular, FontWeight.Normal)
        )
    val semiBold: FontFamily =
        FontFamily(
            Font(R.font.inter_semibold, FontWeight.SemiBold)
        )
    val regular: FontFamily =
        FontFamily(
            Font(R.font.inter_regular, FontWeight.Normal)
        )
    val light: FontFamily =
        FontFamily(
            Font(R.font.inter_light, FontWeight.Light)
        )
}

data class Typography(
    val header1: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    val header2: TextStyle = TextStyle(
        fontSize = 26.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    val header3: TextStyle = TextStyle(
        fontSize = 26.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    val subHeader1: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    val subHeader2: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    val body1: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    val body1Light: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.light
    ),
    val body2: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    val body3: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    val caption1: TextStyle = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    val caption1Bold: TextStyle = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
)
val LocalTypography = compositionLocalOf { Typography() }
