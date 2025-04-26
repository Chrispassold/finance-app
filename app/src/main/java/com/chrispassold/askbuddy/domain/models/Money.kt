package com.chrispassold.askbuddy.domain.models

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Represents an immutable amount of money in a specific currency.
 *
 * @property amount The monetary amount, represented as a BigDecimal for precision.
 * @property currency The currency of the money, represented by a Currency object.
 */
data class Money(val amount: BigDecimal, val currency: Currency) : Comparable<Money> {

    constructor(amount: Double) : this(BigDecimal.valueOf(amount), DEFAULT_CURRENCY)

    init {
        require(amount.scale() <= currency.defaultFractionDigits) {
            "Amount scale cannot be greater than currency default fraction digits"
        }
    }

    /**
     * Formats the money value according to the specified locale.
     *
     * @param locale The locale to use for formatting. Defaults to the system's default locale.
     * @return The formatted money string.
     */
    fun format(locale: Locale = DEFAULT_CURRENCY_LOCALE): String {
        val format = NumberFormat.getCurrencyInstance(locale)
        format.currency = currency
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
        require(currency == other.currency) { "Currencies must be the same for addition" }
        return Money(amount + other.amount, currency)
    }

    /**
     * Subtracts another Money object from this one.
     *
     * @param other The Money object to subtract.
     * @return A new Money object representing the difference.
     * @throws IllegalArgumentException if the currencies are different.
     */
    operator fun minus(other: Money): Money {
        require(currency == other.currency) { "Currencies must be the same for subtraction" }
        return Money(amount - other.amount, currency)
    }

    /**
     * Multiplies this Money object by a scalar value.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new Money object representing the product.
     */
    operator fun times(scalar: BigDecimal): Money {
        return Money(amount * scalar, currency)
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
                java.math.RoundingMode.HALF_UP,
            ),
            currency,
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
        require(currency == other.currency) { "Currencies must be the same for comparison" }
        return amount.compareTo(other.amount)
    }

    companion object {
        val DEFAULT_CURRENCY_LOCALE = Locale("pt", "BR")
        val DEFAULT_CURRENCY = Currency.getInstance(DEFAULT_CURRENCY_LOCALE)

        /**
         * Creates a Money object from a String representation of an amount.
         *
         * @param amount The String representation of the amount.
         * @param currency The currency of the money.
         * @return A new Money object.
         * @throws NumberFormatException if the amount is not a valid number.
         */
        fun fromString(amount: String, currency: Currency): Money {
            return Money(BigDecimal(amount), currency)
        }
    }
}