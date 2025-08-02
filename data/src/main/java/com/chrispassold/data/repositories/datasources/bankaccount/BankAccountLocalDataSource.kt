package com.chrispassold.data.repositories.datasources.bankaccount

import com.chrispassold.domain.models.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountLocalDataSource {
    suspend fun insert(bankAccount: BankAccount)
    suspend fun update(bankAccount: BankAccount)
    suspend fun delete(id: String)
    suspend fun get(id: String): BankAccount?
    fun getAll(): Flow<List<BankAccount>>
}
