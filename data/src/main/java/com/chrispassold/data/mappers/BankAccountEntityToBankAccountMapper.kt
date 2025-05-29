package com.chrispassold.data.mappers

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.Money
import javax.inject.Inject

class BankAccountEntityToBankAccountMapper @Inject constructor() : Mapper<BankAccountEntity, BankAccount> {
    override fun mapTo(from: BankAccountEntity): BankAccount {
        return BankAccount(
            id = from.id,
            name = from.name,
            initialAmount = Money(from.initialAmount),
            hideFromBalance = from.hideFromBalance,
            image = from.image,
            userId = from.userId,
            type = from.type,
        )
    }

}