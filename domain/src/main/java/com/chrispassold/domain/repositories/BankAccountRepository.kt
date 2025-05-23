package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.BankAccount

interface BankAccountRepository {
    suspend fun insert(bankAccount: BankAccount)
    suspend fun update(bankAccount: BankAccount)
    suspend fun delete(id: String)
    suspend fun getAll(): List<BankAccount>
    suspend fun get(id: String): BankAccount?
}