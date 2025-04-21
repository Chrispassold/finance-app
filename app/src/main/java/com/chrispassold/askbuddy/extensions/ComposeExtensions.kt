package com.chrispassold.askbuddy.extensions

import androidx.compose.ui.Modifier


/**
 * Extension function to choose between two functions based on the current UI mode.
 */
fun <T> UiMode.choose(onDarkMode: () -> T, onLightMode: () -> T): T = when (this) {
    UiMode.Dark -> onDarkMode()
    UiMode.Light -> onLightMode()
}

fun Modifier.ifTrue(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier =
    if (condition) {
        then(modifier(this))
    } else {
        this
    }