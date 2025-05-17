package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.data.storage.entities.BankAccountEntity

class BankAccountDataToBankAccountEntityMapper : Mapper<BankAccountData, BankAccountEntity> {
    override fun mapTo(from: BankAccountData): BankAccountEntity {
        return BankAccountEntity(
            id = from.id,
            name = from.name,
            initialAmount = from.initialAmount,
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId
        )
    }
}