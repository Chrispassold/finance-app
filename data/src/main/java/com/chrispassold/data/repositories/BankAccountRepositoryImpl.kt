package com.chrispassold.data.repositories

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.data.repositories.datasources.bankaccount.BankAccountLocalDataSource
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import java.util.UUID
import javax.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(
    private val bankAccountLocalDataSource: BankAccountLocalDataSource,
    private val domainToDataMapper: Mapper<BankAccount, BankAccountData>,
    private val dataToDomainMapper: Mapper<BankAccountData, BankAccount>,
) : BankAccountRepository {
    override suspend fun insertOrUpdate(bankAccount: BankAccount) {
        if (bankAccount.id != null) {
            bankAccountLocalDataSource.update(
                domainToDataMapper.mapTo(
                    bankAccount.copy(
                        id = UUID.randomUUID().toString(),
                    ),
                ),
            )
        } else {
            bankAccountLocalDataSource.insert(domainToDataMapper.mapTo(bankAccount))
        }
    }

    override suspend fun getAll(): List<BankAccount> {
        return dataToDomainMapper.mapToList(
            bankAccountLocalDataSource.getAll(),
        )
    }

    override suspend fun get(id: String): BankAccount? {
        return dataToDomainMapper.mapToNullable(
            bankAccountLocalDataSource.get(id),
        )
    }
}