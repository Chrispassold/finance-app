package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount

class BankAccountToBankAccountEntityMapper : Mapper<BankAccount, BankAccountEntity> {
    override fun mapTo(from: BankAccount): BankAccountEntity {
        return BankAccountEntity(
            id = from.id!!,
            name = from.name,
            initialAmount = from.initialAmount,
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId,
        )
    }

}