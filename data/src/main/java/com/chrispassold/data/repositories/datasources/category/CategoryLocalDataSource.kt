package com.chrispassold.data.repositories.datasources.category

import com.chrispassold.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    suspend fun insert(bankAccount: Category)
    suspend fun update(bankAccount: Category)
    suspend fun delete(id: String)
    suspend fun get(id: String): Category?
    fun getAll(): Flow<List<Category>>
}
