package com.chrispassold.domain.models

/**
 * Enum representing predefined tint colors that a user can select for icons.
 * These are logical names that will be mapped to actual colors in the presentation layer.
 */
enum class IconTint {
    DEFAULT, // Represents a default or no specific tint (e.g., uses icon's inherent color or local content color)
    RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE, PINK, CYAN, GRAY;

    companion object {
        /**
         * Provides a default list of tints that can be offered to the user.
         */
        fun defaultValues(): List<IconTint> = IconTint.entries

        /**
         * Gets an IconTint by its string name, case-insensitively.
         * Returns [DEFAULT] if the name does not match any known tint.
         *
         * @param name The string name of the tint.
         * @return The corresponding [IconTint] or [DEFAULT] if not found.
         */
        fun fromName(name: String?): IconTint {
            return IconTint.entries.find { it.name.equals(name, ignoreCase = true) } ?: DEFAULT
        }
    }
}