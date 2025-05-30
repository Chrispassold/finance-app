package com.chrispassold.presentation.formatters

import com.chrispassold.core.DEFAULT_CURRENCY_LOCALE
import com.chrispassold.core.appLogger
import com.chrispassold.domain.models.Money
import com.chrispassold.presentation.formatters.MoneyFormatter.parseAmount
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

/**
 * A utility object for formatting [Money] objects into locale-specific strings
 * and for parsing string representations of amounts back into [Money] objects.
 *
 * This formatter handles currency symbols, decimal separators, and grouping separators
 * based on the locale specified within the [Money] object or the [DEFAULT_CURRENCY_LOCALE].
 */
object MoneyFormatter {

    /**
     * Formats a [Money] object into a string representation.
     *
     * The formatting is determined by the locale within the [Money] object.
     * It can optionally include the currency prefix (e.g., "$", "€").
     *
     * Example:
     * - `Money(BigDecimal("1234.56"), currency = USD, locale = US)` with `withCurrencyPrefix = true` might format to "$1,234.56".
     * - `Money(BigDecimal("1234.56"), currency = EUR, locale = DE)` with `withCurrencyPrefix = false` might format to "1.234,56".
     *
     * @param money The [Money] object to format.
     * @param withCurrencyPrefix If true, the formatted string will include the currency symbol
     *                           (e.g., "$", "€") as defined by the money's locale and currency.
     *                           If false, only the numeric amount will be formatted according
     *                           to the locale's conventions for number formatting (e.g., decimal
     *                           and grouping separators). Defaults to `false`.
     * @return A string representing the formatted monetary value.
     */
    fun format(
        money: Money,
        withCurrencyPrefix: Boolean = false,
    ): String {
        val format: NumberFormat
        if (withCurrencyPrefix) {
            format = NumberFormat.getCurrencyInstance(money.locale)
            format.currency = money.currency
        } else {
            format = NumberFormat.getNumberInstance(money.locale)
            format.maximumFractionDigits = money.currency.defaultFractionDigits
            format.minimumFractionDigits = money.currency.defaultFractionDigits
            format.isGroupingUsed = true
        }
        return format.format(money.amount)
    }

    /**
     * Parses a string representation of an amount in a specific locale into a [BigDecimal].
     *
     * This method attempts to parse a numeric string, taking into account the conventions
     * of the provided [locale] for decimal and grouping separators.
     * If the string is blank or cannot be parsed, it returns [BigDecimal.ZERO].
     *
     * @param amountString The string representation of the amount to parse.
     * @param locale The [Locale] to use for interpreting the number format (e.g., decimal and grouping separators).
     * @return A [BigDecimal] representing the parsed amount. Returns [BigDecimal.ZERO] if the
     *         `amountString` is blank or if parsing fails.
     */
    private fun parseAmount(amountString: String, locale: Locale): BigDecimal {
        if (amountString.isBlank()) return BigDecimal.ZERO
        val format = NumberFormat.getNumberInstance(locale)
        val number: Number? = try {
            format.parse(amountString)
        } catch (e: java.text.ParseException) {
            // Log parsing errors for better diagnostics if needed
            appLogger.w(e, "Failed to parse amountString: '$amountString' with locale: $locale")
            null
        }
        return when (number) {
            null -> BigDecimal.ZERO
            is BigDecimal -> number
            else -> BigDecimal(number.toString()) // Convert other Number types to BigDecimal
        }
    }

    /**
     * Creates a [Money] object from a string representation of an amount.
     *
     * This method uses the [DEFAULT_CURRENCY_LOCALE] to parse the `amount` string
     * via the [parseAmount] method. It assumes the default currency for the created [Money] object.
     * If parsing fails, an error is logged, and the exception is re-thrown.
     *
     * @param amount The string representation of the amount to convert into a [Money] object.
     * @return A [Money] object representing the parsed amount with the default currency and locale.
     * @throws Throwable if parsing the `amount` string fails.
     */
    fun fromString(amount: String): Money {
        return runCatching {
            Money(parseAmount(amount, DEFAULT_CURRENCY_LOCALE))
        }.onFailure {
            appLogger.e(it, "Error parsing amount: $amount")
        }.getOrThrow()
    }
}