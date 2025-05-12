package com.chrispassold.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val savingsBackground: Color,
    val onSavingsBackground: Color,
    val checkingsBackground: Color,
    val onCheckingsBackground: Color,
)

val MaterialTheme.customColorScheme: CustomColorScheme
    get() = CustomColorScheme(
        savingsBackground = Color(0xFF006874),
        onSavingsBackground = Color(0xFFFFFFFF),
        checkingsBackground = Color(0xFF0061A4),
        onCheckingsBackground = Color(0xFFFFFFFF),
    )