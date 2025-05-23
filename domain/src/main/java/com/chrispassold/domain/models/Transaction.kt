package com.chrispassold.domain.models

import kotlinx.datetime.Instant
import java.math.BigDecimal

data class Transaction(
    val id: String,
    val type: TransactionType,
    val amount: BigDecimal,
    val transactionDate: Instant,
    val description: String,
    val category: Category,
    val bankAccount: BankAccount,
    val userId: String,
)
