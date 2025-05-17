package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.domain.models.BankAccount

class BankAccountDataToBankAccountMapper : Mapper<BankAccountData, BankAccount> {
    override fun mapTo(from: BankAccountData): BankAccount {
        return BankAccount(
            id = from.id,
            name = from.name,
            initialAmount = from.initialAmount,
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId
        )
    }
}