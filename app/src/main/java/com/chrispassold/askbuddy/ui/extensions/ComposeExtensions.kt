package com.chrispassold.askbuddy.ui.extensions


/**
 * Extension function to choose between two functions based on the current UI mode.
 */
fun <T> UiMode.choose(onDarkMode: () -> T, onLightMode: () -> T): T = when (this) {
    UiMode.Dark -> onDarkMode()
    UiMode.Light -> onLightMode()
}