package com.artemissoftware.smartidea.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.smartidea.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

private val NewRodinFont = FontFamily(
    Font(R.font.new_rodin),
    Font(R.font.new_rodin, FontWeight.W500),
    Font(R.font.new_rodin, FontWeight.Bold)
)

val TextNewRodinFont = TextStyle(
    fontFamily = NewRodinFont,
    fontSize = 14.sp
)