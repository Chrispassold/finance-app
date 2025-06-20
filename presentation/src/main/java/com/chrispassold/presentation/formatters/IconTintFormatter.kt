package com.chrispassold.presentation.formatters

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chrispassold.domain.models.IconTint

/**
 * Formatter object to convert [IconTint] enum values to specific [Color] values
 * for use in the UI, typically for tinting icons.
 */
object IconTintFormatter {

    /**
     * Converts an [IconTint] enum to its corresponding Jetpack Compose [Color].
     *
     * The [IconTint.DEFAULT] will often resolve to `Color.Unspecified` to allow
     * the icon to use its inherent color or the `LocalContentColor`.
     *
     * Note: Colors are hardcoded here for simplicity. In a more complex app,
     * these could be sourced from the [MaterialTheme.colorScheme] or a dedicated
     * color palette defined in your theme.
     *
     * @param tint The [IconTint] to convert.
     * @return The Jetpack Compose [Color] representation.
     */
    @Composable
    fun format(tint: IconTint): Color {
        return when (tint) {
            IconTint.DEFAULT -> Color(0xFF757575) // Material Gray 600
            IconTint.RED -> Color(0xFFE53935) // Material Red 600
            IconTint.BLUE -> Color(0xFF1E88E5) // Material Blue 600
            IconTint.GREEN -> Color(0xFF43A047) // Material Green 600
            IconTint.YELLOW -> Color(0xFFFDD835) // Material Yellow 600 (use with caution for visibility)
            IconTint.PURPLE -> Color(0xFF8E24AA) // Material Purple 600
            IconTint.ORANGE -> Color(0xFFFB8C00) // Material Orange 600
            IconTint.PINK -> Color(0xFFD81B60) // Material Pink 600
            IconTint.CYAN -> Color(0xFF00ACC1) // Material Cyan 600
            IconTint.GRAY -> Color(0xFF757575) // Material Gray 600
        }
    }

    /**
     * Provides a list of [IconTint] and their corresponding displayable [Color]
     * suitable for UI pickers.
     *
     * @return A list of pairs, where each pair contains an [IconTint] and its [Color].
     */
    @Composable
    fun getAvailableTintOptions(): Map<IconTint, Color> {
        return IconTint.defaultValues().associateWith { format(it) }
    }
}