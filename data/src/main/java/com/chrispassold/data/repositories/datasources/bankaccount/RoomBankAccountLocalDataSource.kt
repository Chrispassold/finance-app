package com.chrispassold.data.repositories.datasources.bankaccount

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount
import javax.inject.Inject

class RoomBankAccountLocalDataSource @Inject constructor(
    private val bankAccountDao: BankAccountDao,
    private val domainToEntityMapper: Mapper<BankAccount, BankAccountEntity>,
    private val entityToDomainMapper: Mapper<BankAccountEntity, BankAccount>,
) : BankAccountLocalDataSource {
    override suspend fun insert(bankAccount: BankAccount) {
        bankAccountDao.insert(
            domainToEntityMapper.mapTo(bankAccount),
        )
    }

    override suspend fun update(bankAccount: BankAccount) {
        bankAccountDao.update(
            domainToEntityMapper.mapTo(bankAccount),
        )
    }

    override suspend fun delete(id: String) {
        bankAccountDao.deleteById(id)
    }

    override suspend fun get(id: String): BankAccount? {
        return entityToDomainMapper.mapToNullable(bankAccountDao.get(id))
    }

    override suspend fun getAll(): List<BankAccount> {
        return entityToDomainMapper.mapToList(bankAccountDao.getAll())
    }
}