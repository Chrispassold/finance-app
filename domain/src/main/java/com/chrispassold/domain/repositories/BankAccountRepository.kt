package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.BankAccount

interface BankAccountRepository {
    suspend fun insertOrUpdate(bankAccount: BankAccount)
    suspend fun getAll(): List<BankAccount>
    suspend fun get(id: String): BankAccount?
}