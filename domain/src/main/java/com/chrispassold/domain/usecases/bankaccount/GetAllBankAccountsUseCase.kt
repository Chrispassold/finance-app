package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetAllBankAccountsUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {
    fun invoke(): Flow<List<BankAccount>> =
        bankAccountRepository.bankAccounts.flowOn(Dispatchers.IO)
}
