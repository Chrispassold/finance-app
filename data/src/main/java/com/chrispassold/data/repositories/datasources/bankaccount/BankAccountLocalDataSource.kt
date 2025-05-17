package com.chrispassold.data.repositories.datasources.bankaccount

import com.chrispassold.data.models.BankAccountData

interface BankAccountLocalDataSource {
    suspend fun insert(bankAccount: BankAccountData)
    suspend fun update(bankAccount: BankAccountData)
    suspend fun delete(bankAccount: BankAccountData)
    suspend fun get(id: String): BankAccountData?
    suspend fun getAll(): List<BankAccountData>
}