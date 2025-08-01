package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Transaction

interface TransactionRepository {
    suspend fun insert(transaction: Transaction)
    suspend fun update(transaction: Transaction)
    suspend fun delete(id: String)
    suspend fun getAll(): List<Transaction>
    suspend fun get(id: String): Transaction?
}