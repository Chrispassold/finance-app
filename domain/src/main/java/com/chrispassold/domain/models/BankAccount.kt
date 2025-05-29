package com.chrispassold.domain.models

data class BankAccount(
    val id: String,
    val name: String,
    val initialAmount: Money,
    val hideFromBalance: Boolean,
    val image: String?,
    val userId: String,
    val type: BankAccountType,
)