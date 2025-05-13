package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun insert(category: Category): Flow<Unit>
    fun update(category: Category): Flow<Unit>
    fun delete(category: Category): Flow<Unit>
    fun getAll(): Flow<List<Category>>
    fun getById(id: String): Flow<Category?>
}