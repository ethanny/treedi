package com.ar.treedi.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ar.treedi.R


object AppTypography {
    val h1 = TextStyle(
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Medium,
        color = Color(0xFF00573F),
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        letterSpacing = -1.sp
        )

    val h2 = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        letterSpacing = -1.sp
        )

    val b1 = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Normal,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        letterSpacing = -1.sp
        )

}