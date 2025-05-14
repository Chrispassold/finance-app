package com.chrispassold.domain.models

import kotlinx.datetime.Instant

data class Transaction(
    val id: String,
    val type: TransactionType,
    val amount: Money,
    val transactionDate: Instant,
    val description: String,
    val category: Category,
)
