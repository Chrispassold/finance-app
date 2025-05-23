package com.chrispassold.data.repositories.datasources.transaction

import com.chrispassold.domain.models.Transaction

interface TransactionLocalDataSource {
    suspend fun insert(transaction: Transaction)
    suspend fun update(transaction: Transaction)
    suspend fun delete(id: String)
    suspend fun get(id: String): Transaction?
    suspend fun getAll(): List<Transaction>
}