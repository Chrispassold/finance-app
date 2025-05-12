package com.chrispassold.askbuddy.data.storage.entities.customtypes

import java.math.BigDecimal

/**
 * Representa um valor monetário com precisão, armazenado internamente em centavos (como `Long`).
 *
 * ## Por que usar esta classe?
 * Evita problemas de precisão com `Double`, permite usar `BigDecimal` com segurança,
 * e suporta queries numéricas no banco Room ao armazenar valores em centavos.
 *
 */
data class BigCents(private val cents: Long) {

    constructor(value: BigDecimal) : this(value.multiply(BigDecimal(100)).longValueExact())

    fun toBigDecimal(): BigDecimal = BigDecimal(cents).divide(BigDecimal(100))

    fun toLong(): Long = cents

    override fun toString(): String = toBigDecimal().toPlainString()
}