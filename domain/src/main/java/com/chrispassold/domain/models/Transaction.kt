package com.chrispassold.domain.models

import java.util.Date

data class Transaction(
    val id: String,
    val type: String,
    val amount: Money,
    val date: Date,
    val description: String,
    val categoryId: TransactionType,
)
