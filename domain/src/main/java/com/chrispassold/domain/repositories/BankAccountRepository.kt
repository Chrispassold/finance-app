package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    val bankAccounts: Flow<List<BankAccount>>
    suspend fun insert(bankAccount: BankAccount)
    suspend fun update(bankAccount: BankAccount)
    suspend fun delete(id: String)
    suspend fun get(id: String): BankAccount?
}
