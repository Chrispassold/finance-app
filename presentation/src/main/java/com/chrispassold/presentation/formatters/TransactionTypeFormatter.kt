package com.chrispassold.presentation.formatters

import com.chrispassold.domain.models.TransactionType

object TransactionTypeFormatter {
    fun format(transactionType: TransactionType): String {
        return when (transactionType) {
            TransactionType.EXPENSE -> "Expense"
            TransactionType.INCOME -> "Income"
        }
    }
}