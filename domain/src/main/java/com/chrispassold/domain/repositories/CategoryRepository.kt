package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val categories: Flow<List<Category>>
    suspend fun insert(category: Category)
    suspend fun update(category: Category)
    suspend fun delete(id: String)
    suspend fun get(id: String): Category?
}
