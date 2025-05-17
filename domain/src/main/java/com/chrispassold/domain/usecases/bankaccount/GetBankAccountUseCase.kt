package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.DataNotFoundException
import com.chrispassold.domain.repositories.BankAccountRepository
import javax.inject.Inject

class GetBankAccountUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {

    data class Params(
        val bankAccountId: String,
    )

    suspend fun invoke(params: Params): Result<BankAccount> = runCatching {
        bankAccountRepository.get(params.bankAccountId)
            ?: throw DataNotFoundException("Bank account not found")
    }

}