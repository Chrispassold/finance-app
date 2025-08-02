package com.chrispassold.data.repositories

import com.chrispassold.data.repositories.datasources.bankaccount.BankAccountLocalDataSource
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class BankAccountRepositoryImpl @Inject constructor(
    private val bankAccountLocalDataSource: BankAccountLocalDataSource,
) : BankAccountRepository {
    override val bankAccounts: Flow<List<BankAccount>>
        get() = bankAccountLocalDataSource.getAll()

    override suspend fun insert(bankAccount: BankAccount) {
        bankAccountLocalDataSource.insert(bankAccount)
    }

    override suspend fun update(bankAccount: BankAccount) {
        bankAccountLocalDataSource.update(bankAccount)
    }

    override suspend fun delete(id: String) {
        bankAccountLocalDataSource.delete(id)
    }

    override suspend fun get(id: String): BankAccount? {
        return bankAccountLocalDataSource.get(id)
    }
}
