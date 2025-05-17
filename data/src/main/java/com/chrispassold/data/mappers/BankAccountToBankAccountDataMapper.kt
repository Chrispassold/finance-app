package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.domain.models.BankAccount

class BankAccountToBankAccountDataMapper : Mapper<BankAccount, BankAccountData> {
    override fun mapTo(from: BankAccount): BankAccountData {
        return BankAccountData(
            id = from.id,
            name = from.name,
            initialAmount = from.initialAmount,
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId
        )
    }
}