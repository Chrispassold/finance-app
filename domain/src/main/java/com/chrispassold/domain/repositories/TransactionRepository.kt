package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun insert(transaction: Transaction): Flow<Unit>
    fun update(transaction: Transaction): Flow<Unit>
    fun delete(transaction: Transaction): Flow<Unit>
    fun getAll(): Flow<List<Transaction>>
    fun getById(id: String): Flow<Transaction?>
}