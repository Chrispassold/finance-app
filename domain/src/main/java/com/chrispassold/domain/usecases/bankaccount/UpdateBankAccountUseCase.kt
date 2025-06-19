package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.repositories.BankAccountRepository
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import java.math.BigDecimal
import javax.inject.Inject

class UpdateBankAccountUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getBankAccountUseCase: GetBankAccountUseCase,
    private val bankAccountRepository: BankAccountRepository,
) {

    data class Params(
        val name: String,
        val initialAmount: BigDecimal,
        val hideFromBalance: Boolean,
        val type: BankAccountType?,
        val image: String?,
        val id: String,
    )

    suspend fun invoke(params: Params): Result<Unit> = resultWithContext(Dispatchers.IO) {
        require(params.type != null) { "Type cannot be null" }

        val user = userRepository.getCurrentUser() ?: error("User not logged in")

        getBankAccountUseCase.invoke(GetBankAccountUseCase.Params(params.id)).getOrThrow().copy(
            name = params.name,
            initialAmount = params.initialAmount,
            hideFromBalance = params.hideFromBalance,
            image = params.image,
            type = params.type,
            userId = user.id,
        ).let { bankAccountRepository.update(it) }
    }

}