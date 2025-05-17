package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.repositories.BankAccountRepository
import javax.inject.Inject

class CreateBankAccountUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {

    data class Params(
        val bankAccount: BankAccount,
    )

    suspend fun invoke(params: Params): Result<Unit> = runCatching {
        bankAccountRepository.insertOrUpdate(params.bankAccount)
    }

}