package com.chrispassold.askbuddy.presentation.components.inputs

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * A [VisualTransformation] that formats a numerical input string as a monetary value,
 * treating the input as cents and automatically inserting the decimal separator.
 *
 * Example:
 * - Input "1" becomes "0.01" (or "0,01" based on locale)
 * - Input "12" becomes "0.12"
 * - Input "123" becomes "1.23"
 * - Input "12345" becomes "123.45"
 *
 * @param locale The locale to use for formatting, determining the decimal and grouping separators.
 */
class MoneyInputVisualTransformation(private val locale: Locale) : VisualTransformation {

    // Use DecimalFormat to handle locale-specific formatting
    private val formatter = DecimalFormat("#,##0.00", DecimalFormatSymbols(locale))

    override fun filter(inputText: AnnotatedString): TransformedText {
        val digits = inputText.text.filter { it.isDigit() }
        if (digits.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        // Pad with leading zeros if less than 2 digits (for cents)
        val paddedDigits = digits.padStart(2, '0')

        // Calculate the position of the decimal point from the right in the padded digits
        val decimalPointPositionInPaddedDigits = paddedDigits.length - 2

        // Insert the decimal point internally for BigDecimal parsing
        val integerPart = paddedDigits.substring(0, decimalPointPositionInPaddedDigits)
        val fractionalPart = paddedDigits.substring(decimalPointPositionInPaddedDigits)
        val numberString = "$integerPart.$fractionalPart" // Use period for internal representation

        // Parse to BigDecimal and format using the locale-specific formatter
        val formattedText = runCatching {
            formatter.format(BigDecimal(numberString))
        }.getOrDefault(numberString) // Fallback if formatting fails

        // Create the OffsetMapping to correctly position the cursor
        val originalTextLength = digits.length
        val formattedTextLength = formattedText.length

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Map cursor from original input (digits) to the formatted string.

                // Adjust offset based on padding. If original input was short,
                // a smaller offset might correspond to a position after leading zeros.
                val adjustedOffsetInDigits = offset.coerceIn(0, digits.length).let {
                    if (originalTextLength < 2) {
                        it + (2 - originalTextLength).coerceAtLeast(0)
                    } else {
                        it
                    }
                }.coerceAtLeast(0) // Ensure non-negative

                // Now, map the adjusted offset (in padded digits) to the formatted string.
                // We need to consider the position relative to the decimal point and grouping separators.

                var transformedOffset = adjustedOffsetInDigits

                // Account for the decimal separator
                if (adjustedOffsetInDigits > decimalPointPositionInPaddedDigits) {
                    transformedOffset++ // Add 1 for the decimal point
                }

                // Account for grouping separators in the integer part
                // This part is still complex and depends on the formatter's specific grouping.
                // For the ###,##0.00 format, grouping is every 3 digits before the decimal.
                // We need to count how many grouping separators would appear before the cursor's position
                // in the integer part of the formatted string.

                val integerDigitsBeforeOffsetInPadded = paddedDigits.substring(
                    0,
                    adjustedOffsetInDigits.coerceAtMost(decimalPointPositionInPaddedDigits),
                )
                // A simpler but potentially less accurate approach for grouping offset:
                // Count grouping separators in the formatted text up to the estimated transformed position.
                // This assumes a rough linear mapping first.
                val estimatedTransformedPosition =
                    (adjustedOffsetInDigits.toFloat() / paddedDigits.length.toFloat() * formattedTextLength).toInt()
                val groupingSeparatorCount =
                    formattedText.substring(0, estimatedTransformedPosition)
                        .count { it == formatter.decimalFormatSymbols.groupingSeparator }

                // transformedOffset += groupingSeparatorCount // Add grouping separator count (approximation)

                // A more precise approach requires simulating the formatting process to find the exact index.
                // This is non-trivial. For the sake of providing a functional example,
                // we'll use a simplified mapping with basic decimal point handling and
                // acknowledge the complexity of precise grouping offset mapping.

                // Let's retry a simpler but more direct mapping based on positions.
                transformedOffset = 0
                var originalIdx = 0
                var formattedIdx = 0

                while (originalIdx < offset && formattedIdx < formattedTextLength) {
                    // Find the next digit in the formatted text
                    while (formattedIdx < formattedTextLength && !formattedText[formattedIdx].isDigit()) {
                        formattedIdx++
                        if (formattedIdx < formattedTextLength && formattedText[formattedIdx].isDigit()) {
                            transformedOffset++
                        }
                    }

                    if (formattedIdx < formattedTextLength) {
                        transformedOffset++
                        originalIdx++
                        formattedIdx++
                    }
                }

                // Adjust for decimal point if original offset is after decimal position
                if (offset > digits.length - 2 && digits.isNotEmpty() && formattedTextLength > 2) {
                    // Find the position of the decimal separator in the formatted text
                    val decimalSeparatorIndex =
                        formattedText.indexOf(formatter.decimalFormatSymbols.decimalSeparator)
                    if (decimalSeparatorIndex != -1 && transformedOffset <= decimalSeparatorIndex) {
                        transformedOffset = decimalSeparatorIndex + (offset - (digits.length - 2))
                    }
                }


                return transformedOffset.coerceIn(0, formattedTextLength)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val originalTextLength = digits.length

                // If the original input was empty, the only valid original offset is 0.
                if (originalTextLength == 0) {
                    return 0
                }

                var originalOffset = 0
                var transformedIdx = 0
                var originalIdx = 0

                while (transformedIdx < offset && originalIdx < digits.length) {
                    // Find the next digit in the formatted text
                    while (transformedIdx < formattedTextLength && !formattedText[transformedIdx].isDigit()) {
                        transformedIdx++
                    }

                    if (transformedIdx < formattedTextLength) {
                        originalOffset++
                        originalIdx++
                        transformedIdx++
                    }
                }

                // Account for leading zeros if the original input was shorter than 2 digits
                if (originalTextLength < 2) {
                    originalOffset = originalOffset.coerceAtMost(originalTextLength)
                }

                return originalOffset.coerceIn(0, originalTextLength)
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}