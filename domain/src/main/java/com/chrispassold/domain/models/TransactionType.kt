package com.chrispassold.domain.models

private const val EXPENSE_DATABASE_NAME = "expense"
private const val INCOME_DATABASE_NAME = "income"

enum class TransactionType(val databaseName: String) {
    EXPENSE(
        databaseName = EXPENSE_DATABASE_NAME,
    ),
    INCOME(
        databaseName = INCOME_DATABASE_NAME,
    );

    companion object {
        fun fromDatabaseName(name: String): TransactionType? {
            return TransactionType.entries.find { it.databaseName == name }
        }
    }
}