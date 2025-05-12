package com.chrispassold.presentation.extensions

import androidx.compose.runtime.compositionLocalOf

// Define a data class to represent the UI mode
enum class UiMode {
    Dark, Light
}

val LocalUiMode = compositionLocalOf { UiMode.Light }