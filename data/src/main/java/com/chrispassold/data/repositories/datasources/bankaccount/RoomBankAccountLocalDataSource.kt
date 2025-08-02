package com.chrispassold.data.repositories.datasources.bankaccount

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.DatabaseException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override fun getAll(): Flow<List<BankAccount>> {
        return try {
            bankAccountDao.getAll().map {
                entityToDomainMapper.mapToList(it)
            }
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

}
