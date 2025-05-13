package com.chrispassold.domain.models

import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.text.ParseException
import java.util.Currency
import java.util.Locale

private fun Locale.getCurrency(): Currency = Currency.getInstance(this)

/**
 * Represents an immutable amount of money in a specific currency.
 *
 * @property amount The monetary amount, represented as a BigDecimal for precision.
 * @property currency The currency of the money, represented by a Currency object.
 */
data class Money(val amount: BigDecimal, val locale: Locale) : Comparable<Money> {

    private val currency: Currency = locale.getCurrency()

    constructor(amount: BigDecimal) : this(amount = amount, locale = DEFAULT_CURRENCY_LOCALE)

    constructor(amount: Double, locale: Locale = DEFAULT_CURRENCY_LOCALE) : this(
        BigDecimal.valueOf(
            amount,
        ),
        locale,
    )

    /**
     * Creates a Money object from a String representation of an amount,
     * using the currency and locale for parsing.
     *
     * @param amount The String representation of the amount.
     * @throws ParseException if the amount string is not in a parsable format according to the specified locale.
     */
    private constructor(
        amount: String,
        locale: Locale = DEFAULT_CURRENCY_LOCALE,
    ) : this(
        parseAmount(amount, locale).setScale(
            locale.getCurrency().defaultFractionDigits,
            RoundingMode.HALF_UP,
        ),
        locale,
    )

    init {
        // This check might need adjustment if you are parsing strings with different scales
        // depending on the locale and format. However, for basic cases, keeping it is fine.
        require(amount.scale() <= currency.defaultFractionDigits) {
            "Amount scale (${amount.scale()}) cannot be greater than currency default fraction digits (${currency.defaultFractionDigits})"
        }
    }

    /**
     * Formats the money value according to the specified locale and includes or excludes the currency symbol based on the [withCurrencyPrefix] parameter.
     *
     * @param locale The locale to use for formatting. Defaults to the system's default locale.
     * @param withCurrencyPrefix If true, includes the currency symbol in the formatted string. If false, excludes it. Defaults to true.
     * @return The formatted money string, with or without the currency symbol based on the [withCurrencyPrefix] parameter.
     */
    fun format(
        locale: Locale = DEFAULT_CURRENCY_LOCALE,
        withCurrencyPrefix: Boolean = true,
    ): String {
        val format: NumberFormat
        if (withCurrencyPrefix) {
            format = NumberFormat.getCurrencyInstance(locale)
            format.currency = currency
        } else {
            format = NumberFormat.getNumberInstance(locale)
            format.maximumFractionDigits = currency.defaultFractionDigits
            format.minimumFractionDigits = currency.defaultFractionDigits
            format.isGroupingUsed =
                true // Typically you want grouping (e.g., commas) for larger numbers
        }
        return format.format(amount)
    }

    /**
     * Adds another Money object to this one.
     *
     * @param other The Money object to add.
     * @return A new Money object representing the sum.
     * @throws IllegalArgumentException if the currencies are different.
     */
    operator fun plus(other: Money): Money {
        require(locale == other.locale) { "Locale must be the same for addition" }
        return Money(amount + other.amount, locale)
    }

    /**
     * Subtracts another Money object from this one.
     *
     * @param other The Money object to subtract.
     * @return A new Money object representing the difference.
     * @throws IllegalArgumentException if the currencies are different.
     */
    operator fun minus(other: Money): Money {
        require(locale == other.locale) { "Locale must be the same for subtraction" }
        return Money(amount - other.amount, locale)
    }

    /**
     * Multiplies this Money object by a scalar value.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new Money object representing the product.
     */
    operator fun times(scalar: BigDecimal): Money {
        return Money(amount * scalar, locale)
    }

    /**
     * Divides this Money object by a scalar value.
     *
     * @param scalar The scalar value to divide by.
     * @return A new Money object representing the quotient.
     * @throws ArithmeticException if the scalar is zero.
     */
    operator fun div(scalar: BigDecimal): Money {
        return Money(
            amount.divide(
                scalar,
                currency.defaultFractionDigits,
                RoundingMode.HALF_UP,
            ),
            locale,
        )
    }

    /**
     * Compares this Money object to another one.
     *
     * @param other The Money object to compare to.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @throws IllegalArgumentException if the currencies are different.
     */
    override fun compareTo(other: Money): Int {
        require(locale == other.locale) { "Currencies must be the same for comparison" }
        return amount.compareTo(other.amount)
    }

    /**
     * Checks if this Money value is equal to zero.
     *
     * @return True if the amount is zero, false otherwise.
     */
    fun isZero(): Boolean = amount.compareTo(BigDecimal.ZERO) == 0

    /**
     * Returns a string representation of the money value.
     *
     * @return The formatted money string with default locale.
     */
    override fun toString(): String {
        return this.format(withCurrencyPrefix = false)
    }

    companion object {
        val DEFAULT_CURRENCY_LOCALE = Locale("pt", "BR")
        fun zero(locale: Locale = DEFAULT_CURRENCY_LOCALE) = Money(BigDecimal.ZERO, locale)

        /**
         * Parses a string representation of a monetary amount into a BigDecimal.
         *
         * @param amountString The string to parse.
         * @param locale The locale to use for parsing.
         * @return The parsed BigDecimal amount.
         * @throws ParseException if the amount string is not in a parsable format.
         */
        private fun parseAmount(amountString: String, locale: Locale): BigDecimal {
            if (amountString.isEmpty()) return BigDecimal.ZERO
            val format = NumberFormat.getNumberInstance(locale)
            val number = format.parse(amountString)
            return when (number) {
                null -> BigDecimal.ZERO
                is BigDecimal -> number
                else -> BigDecimal(number.toString())
            }
        }

        /**
         * Creates a Money object from a String representation of an amount.
         *
         * @param amount The String representation of the amount.
         * @param currency The currency of the money.
         * @return A new Money object.
         * @throws ParseException if the amount string is not in a parsable format.
         */
        fun fromString(amount: String, locale: Locale = DEFAULT_CURRENCY_LOCALE): Money {
            return runCatching {
                Money(amount, locale)
            }.onFailure {
                Timber.e(it, "Error parsing amount: $amount")
                // Rethrow or handle the exception as appropriate for your application
                throw it
            }.getOrThrow()
        }
    }
}