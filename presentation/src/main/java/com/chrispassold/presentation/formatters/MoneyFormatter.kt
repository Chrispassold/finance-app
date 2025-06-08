package com.chrispassold.presentation.formatters

import com.chrispassold.core.DEFAULT_CURRENCY_LOCALE
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

object MoneyFormatter {
    fun format(value: BigDecimal, locale: Locale = DEFAULT_CURRENCY_LOCALE): String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(value)
    }

    fun parse(input: String, locale: Locale = DEFAULT_CURRENCY_LOCALE): BigDecimal? {
        val format = NumberFormat.getCurrencyInstance(locale)
        return try {
            val number = format.parse(input)
            number?.toDouble()?.toBigDecimal()
        } catch (e: Exception) {
            null
        }
    }
}
