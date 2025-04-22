package com.chrispassold.askbuddy.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode


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

/**
 * Checks if the current context is in preview mode.
 *
 * @return `true` if in preview mode, `false` otherwise.
 */
@Composable
@ReadOnlyComposable
fun isPreview(): Boolean {
    return LocalInspectionMode.current
}

/**
 * Extension function to apply different functions based on whether the current local inspection is in preview mode.
 *
 * @param ifPreview The function to execute if in preview mode.
 * @param ifNotPreview The function to execute if not in preview mode.
 * @return The result of the executed function.
 */
@Composable
fun <T> whenPreview(ifPreview: () -> T, ifNotPreview: () -> T): T {
    return if (isPreview()) {
        ifPreview()
    } else {
        ifNotPreview()
    }
}