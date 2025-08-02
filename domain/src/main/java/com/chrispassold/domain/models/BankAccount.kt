package com.chrispassold.domain.models

import java.math.BigDecimal

data class BankAccount(
    val id: String,
    val name: String,
    val initialAmount: BigDecimal,
    val hideFromBalance: Boolean,
    val image: IconType,
    val userId: String,
    val type: BankAccountType,
)
