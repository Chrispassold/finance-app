package com.chrispassold.data.models

import java.math.BigDecimal

data class BankAccountData(
    val id: String,
    val name: String,
    val initialAmount: BigDecimal,
    val hideFromBalance: Boolean,
    val image: String,
    val userId: String,
)