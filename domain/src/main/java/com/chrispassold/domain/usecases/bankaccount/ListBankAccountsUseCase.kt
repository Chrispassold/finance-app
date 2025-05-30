package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ListBankAccountsUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {

    class Params()

    suspend fun invoke(@Suppress("unused") params: Params): Result<List<BankAccount>> =
        resultWithContext(Dispatchers.IO) {
            bankAccountRepository.getAll()
        }
}