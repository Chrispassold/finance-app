package com.chrispassold.data.repositories.datasources.category

import com.chrispassold.domain.models.Category

interface CategoryLocalDataSource {
    suspend fun insert(bankAccount: Category)
    suspend fun update(bankAccount: Category)
    suspend fun delete(id: String)
    suspend fun get(id: String): Category?
    suspend fun getAll(): List<Category>
    suspend fun getSubCategoriesFor(parentId: String): List<Category>
}