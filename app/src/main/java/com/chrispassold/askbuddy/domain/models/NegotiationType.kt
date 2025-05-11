package com.chrispassold.askbuddy.domain.models

private const val EXPENSE_DATABASE_NAME = "expense"
private const val INCOME_DATABASE_NAME = "income"

enum class NegotiationType(val databaseName: String) {
    EXPENSE(
        databaseName = EXPENSE_DATABASE_NAME,
    ),
    INCOME(
        databaseName = INCOME_DATABASE_NAME,
    );

    companion object {
        fun fromDatabaseName(name: String): NegotiationType? {
            return NegotiationType.entries.find { it.databaseName == name }
        }
    }
}