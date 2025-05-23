package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.entities.TransactionEntity
import com.chrispassold.domain.models.Transaction

class TransactionToTransactionEntityMapper : Mapper<Transaction, TransactionEntity> {
    override fun mapTo(from: Transaction): TransactionEntity {
        return TransactionEntity(
            id = from.id,
            userId = from.userId,
            type = from.type,
            amount = from.amount,
            transactionDate = from.transactionDate,
            description = from.description,
            categoryId = from.category.id,
            bankAccountId = from.bankAccount.id,
        )
    }
}