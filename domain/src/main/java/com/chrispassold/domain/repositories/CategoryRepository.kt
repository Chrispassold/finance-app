package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.Category

interface CategoryRepository {
    suspend fun insertOrUpdate(category: Category)
    suspend fun delete(id: String)
    suspend fun getAll(): List<Category>
    suspend fun get(id: String): Category?
}