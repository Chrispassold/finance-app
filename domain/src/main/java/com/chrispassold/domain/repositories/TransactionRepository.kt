package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertOrUpdate(transaction: Transaction)
    fun getAll(): Flow<List<Transaction>>
    fun get(id: String): Flow<Transaction?>
}