package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.data.storage.entities.BankAccountEntity

class BankAccountEntityToBankAccountDataMapper : Mapper<BankAccountEntity, BankAccountData> {
    override fun mapTo(from: BankAccountEntity): BankAccountData {
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