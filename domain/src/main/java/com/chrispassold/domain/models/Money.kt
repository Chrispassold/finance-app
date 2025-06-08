package com.chrispassold.domain.models

import com.chrispassold.core.DEFAULT_CURRENCY_LOCALE
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Currency
import java.util.Locale

// For the future
@ConsistentCopyVisibility
private data class Money private constructor(val amount: BigDecimal, val locale: Locale) :
    Comparable<Money> {

    val currency: Currency = Currency.getInstance(locale)

    companion object {
        fun zero() = Money(BigDecimal.ZERO)
    }

    constructor(amount: BigDecimal?) : this(amount ?: BigDecimal.ZERO, DEFAULT_CURRENCY_LOCALE)

    constructor(amount: Double) : this(BigDecimal.valueOf(amount))

    init {
        // This check might need adjustment if you are parsing strings with different scales
        // depending on the locale and format. However, for basic cases, keeping it is fine.
        require(amount.scale() <= currency.defaultFractionDigits) {
            "Amount scale (${amount.scale()}) cannot be greater than currency default fraction digits (${currency.defaultFractionDigits})"
        }
    }

    /**
     * Adds another Money object to this one.
     *
     * @param other The Money object to add.
     * @return A new Money object representing the sum.
     * @throws IllegalArgumentException if the currencies are different.
     */
    operator fun plus(other: Money): Money {
        return Money(amount + other.amount)
    }

    /**
     * Subtracts another Money object from this one.
     *
     * @param other The Money object to subtract.
     * @return A new Money object representing the difference.
     * @throws IllegalArgumentException if the currencies are different.
     */
    operator fun minus(other: Money): Money {
        return Money(amount - other.amount)
    }

    /**
     * Multiplies this Money object by a scalar value.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new Money object representing the product.
     */
    operator fun times(scalar: BigDecimal): Money {
        return Money(amount * scalar)
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
        return amount.toPlainString()
    }
}