package com.chrispassold.data.repositories.datasources.bankaccount

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.entities.BankAccountEntity
import javax.inject.Inject

class RoomBankAccountLocalDataSource @Inject constructor(
    private val bankAccountDao: BankAccountDao,
    private val entityToDataMapper: Mapper<BankAccountEntity, BankAccountData>,
    private val dataToEntityMapper: Mapper<BankAccountData, BankAccountEntity>,
) : BankAccountLocalDataSource {
    override suspend fun insert(bankAccount: BankAccountData) {
        bankAccountDao.insert(
            dataToEntityMapper.mapTo(bankAccount),
        )
    }

    override suspend fun update(bankAccount: BankAccountData) {
        bankAccountDao.update(
            dataToEntityMapper.mapTo(bankAccount),
        )
    }

    override suspend fun delete(bankAccount: BankAccountData) {
        bankAccountDao.delete(
            dataToEntityMapper.mapTo(bankAccount),
        )
    }

    override suspend fun get(id: String): BankAccountData? {
        return entityToDataMapper.mapToNullable(bankAccountDao.get(id))
    }

    override suspend fun getAll(): List<BankAccountData> {
        return entityToDataMapper.mapToList(bankAccountDao.getAll())
    }
}