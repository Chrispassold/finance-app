package com.chrispassold.domain.usecases.transaction

import com.chrispassold.domain.models.Transaction
import com.chrispassold.domain.repositories.TransactionRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    suspend fun invoke(transaction: Transaction): Result<Unit> = runCatching {
        transactionRepository.insert(transaction)
    }

}