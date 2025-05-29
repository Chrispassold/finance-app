package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.domain.models.TransactionType

private const val EXPENSE_DATABASE_NAME = "expense"
private const val INCOME_DATABASE_NAME = "income"

internal class TransactionTypeConverter {

    @TypeConverter
    fun fromTransactionType(type: TransactionType?): String? {
        return when (type) {
            TransactionType.EXPENSE -> EXPENSE_DATABASE_NAME
            TransactionType.INCOME -> INCOME_DATABASE_NAME
            null -> null
        }
    }

    @TypeConverter
    fun toTransactionType(name: String?): TransactionType? {
        return when (name) {
            EXPENSE_DATABASE_NAME -> TransactionType.EXPENSE
            INCOME_DATABASE_NAME -> TransactionType.INCOME
            else -> null
        }
    }
}