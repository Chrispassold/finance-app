package com.chrispassold.data.mappers

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.TransactionWithDetailsEntity
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.Transaction
import javax.inject.Inject

class TransactionWithDetailsEntityToTransactionMapper @Inject constructor(
    private val categoryMapper: Mapper<CategoryEntity, Category>,
    private val bankAccountMapper: Mapper<BankAccountEntity, BankAccount>,
) : Mapper<TransactionWithDetailsEntity, Transaction> {
    override fun mapTo(from: TransactionWithDetailsEntity): Transaction {
        return Transaction(
            id = from.transactionEntity.id,
            amount = from.transactionEntity.amount,
            transactionDate = from.transactionEntity.transactionDate,
            description = from.transactionEntity.description,
            category = categoryMapper.mapTo(from.categoryEntity),
            bankAccount = bankAccountMapper.mapTo(from.bankAccountEntity),
            type = from.transactionEntity.type,
            userId = from.transactionEntity.userId,
        )
    }

}