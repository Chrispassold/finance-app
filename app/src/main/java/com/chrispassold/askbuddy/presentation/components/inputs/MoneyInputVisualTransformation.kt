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
 * The cursor is always positioned at the end of the formatted value.
 *
 * Example:
 * - Input "1" becomes "0.01" (or "0,01" based on locale), cursor at the end.
 * - Input "12" becomes "0.12", cursor at the end.
 * - Input "123" becomes "1.23", cursor at the end.
 * - Input "12345" becomes "123.45", cursor at the end.
 *
 * @param locale The locale to use for formatting, determining the decimal and grouping separators.
 */
class MoneyInputVisualTransformation(private val locale: Locale) : VisualTransformation {

    // Use DecimalFormat to handle locale-specific formatting
    private val formatter = DecimalFormat("#,##0.00", DecimalFormatSymbols(locale))

    override fun filter(inputText: AnnotatedString): TransformedText {
        val digits = inputText.text.filter { it.isDigit() }
        if (digits.isEmpty()) {
            return TransformedText(
                AnnotatedString(""),
                OffsetMapping.Identity,
            )
        }
        val paddedDigits = digits.padStart(2, '0')
        val decimalPointPositionInPaddedDigits = paddedDigits.length - 2
        val integerPart = paddedDigits.substring(0, decimalPointPositionInPaddedDigits)
        val fractionalPart = paddedDigits.substring(decimalPointPositionInPaddedDigits)
        val numberString = "$integerPart.$fractionalPart"
        val formattedText = runCatching {
            formatter.format(BigDecimal(numberString))
        }.getOrDefault(numberString)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return formattedText.length.coerceIn(0, formattedText.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val originalDigits = inputText.text.filter { it.isDigit() }
                val originalTextLength = originalDigits.length
                val formattedTextLength = formattedText.length
                if (offset >= formattedTextLength) {
                    return originalTextLength
                }
                return 0
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}