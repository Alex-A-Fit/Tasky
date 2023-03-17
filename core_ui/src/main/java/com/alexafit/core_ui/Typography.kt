package com.alexafit.core_ui

import androidx.compose.material.Typography
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

val Typography = Typography(
    h1 = TextStyle(
        fontSize = 28.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    h2 = TextStyle(
        fontSize = 26.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    h3 = TextStyle(
        fontSize = 26.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    subtitle1 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    subtitle2 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.bold
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    ),
    caption = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        fontFamily = Inter.regular
    )
    )