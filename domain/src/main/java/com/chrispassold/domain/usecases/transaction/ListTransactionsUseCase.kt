package com.chrispassold.domain.usecases.transaction

import com.chrispassold.domain.models.Transaction
import com.chrispassold.domain.repositories.TransactionRepository
import javax.inject.Inject

class ListTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    suspend fun invoke(): Result<List<Transaction>> = runCatching {
        transactionRepository.getAll()
    }

}