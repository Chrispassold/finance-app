package com.chrispassold.data.repositories.datasources.transaction

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.dao.TransactionDao
import com.chrispassold.data.storage.entities.TransactionEntity
import com.chrispassold.data.storage.entities.TransactionWithDetailsEntity
import com.chrispassold.domain.models.Transaction
import javax.inject.Inject

class RoomTransactionLocalDataSource @Inject constructor(
    private val transactionDao: TransactionDao,
    private val entityToDomainMapper: Mapper<TransactionWithDetailsEntity, Transaction>,
    private val domainToEntityMapper: Mapper<Transaction, TransactionEntity>,
) : TransactionLocalDataSource {
    override suspend fun insert(transaction: Transaction) {
        transactionDao.insert(domainToEntityMapper.mapTo(transaction))
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(domainToEntityMapper.mapTo(transaction))
    }

    override suspend fun delete(id: String) {
        transactionDao.deleteById(id)
    }

    override suspend fun get(id: String): Transaction? {
        return transactionDao.getWithDetails(id)?.let { entityToDomainMapper.mapTo(it) }
    }

    override suspend fun getAll(): List<Transaction> {
        return transactionDao.getAllWithDetails().map { entityToDomainMapper.mapTo(it) }
    }
}