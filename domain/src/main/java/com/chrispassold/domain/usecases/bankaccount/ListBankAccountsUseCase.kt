package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import javax.inject.Inject

class ListBankAccountsUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {

    class Params()

    suspend fun invoke(@Suppress("unused") params: Params): Result<List<BankAccount>> =
        runCatching {
            bankAccountRepository.getAll()
        }
}