package com.chrispassold.domain.usecases.transaction

import com.chrispassold.domain.models.DataNotFoundException
import com.chrispassold.domain.models.Transaction
import com.chrispassold.domain.repositories.TransactionRepository
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    suspend fun invoke(transactionId: String): Result<Transaction> = runCatching {
        transactionRepository.get(transactionId)
            ?: throw DataNotFoundException("Bank account not found")
    }

}