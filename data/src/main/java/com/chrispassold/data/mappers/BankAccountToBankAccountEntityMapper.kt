package com.chrispassold.data.mappers

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount
import javax.inject.Inject

class BankAccountToBankAccountEntityMapper @Inject constructor() : Mapper<BankAccount, BankAccountEntity> {
    override fun mapTo(from: BankAccount): BankAccountEntity {
        return BankAccountEntity(
            id = from.id,
            name = from.name,
            initialAmount = from.initialAmount.amount,
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId,
            type = from.type
        )
    }

}