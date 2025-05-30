package com.chrispassold.domain.usecases.bankaccount

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.models.Money
import com.chrispassold.domain.repositories.BankAccountRepository
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

class CreateOrUpdateBankAccountUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getBankAccountUseCase: GetBankAccountUseCase,
    private val bankAccountRepository: BankAccountRepository,
) {

    data class Params(
        val name: String?,
        val initialAmount: BigDecimal?,
        val hideFromBalance: Boolean,
        val type: BankAccountType?,
        val image: String?,
        val id: String? = null,
    )

    suspend fun invoke(params: Params): Result<Unit> = resultWithContext(Dispatchers.IO) {
        require(params.name != null) { "Name cannot be null" }
        require(params.type != null) { "Type cannot be null" }

        val user = userRepository.getCurrentUser() ?: error("User not logged in")

        if (params.id != null) {
            getBankAccountUseCase.invoke(GetBankAccountUseCase.Params(params.id)).getOrThrow().copy(
                name = params.name,
                initialAmount = Money(params.initialAmount),
                hideFromBalance = params.hideFromBalance,
                image = params.image,
                type = params.type,
            ).let { bankAccountRepository.update(it) }
        } else {
            BankAccount(
                id = UUID.randomUUID().toString(),
                name = params.name,
                initialAmount = Money(params.initialAmount),
                hideFromBalance = params.hideFromBalance,
                image = params.image,
                userId = user.id,
                type = params.type,
            ).let { bankAccountRepository.insert(it) }
        }
    }

}