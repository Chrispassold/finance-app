package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    fun insert(bankAccount: BankAccount): Flow<Unit>
    fun update(bankAccount: BankAccount): Flow<Unit>
    fun delete(bankAccount: BankAccount): Flow<Unit>
    fun getAll(): Flow<List<BankAccount>>
    fun getById(id: String): Flow<BankAccount?>
}