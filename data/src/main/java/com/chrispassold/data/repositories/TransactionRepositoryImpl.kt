package com.chrispassold.data.repositories

import com.chrispassold.data.repositories.datasources.transaction.TransactionLocalDataSource
import com.chrispassold.domain.models.Transaction
import com.chrispassold.domain.repositories.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionLocalDataSource: TransactionLocalDataSource,
) : TransactionRepository {
    override suspend fun insert(transaction: Transaction) {
        transactionLocalDataSource.insert(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactionLocalDataSource.update(transaction)
    }

    override suspend fun delete(id: String) {
        transactionLocalDataSource.delete(id)
    }

    override suspend fun getAll(): List<Transaction> {
        return transactionLocalDataSource.getAll()
    }

    override suspend fun get(id: String): Transaction? {
        return transactionLocalDataSource.get(id)
    }
}