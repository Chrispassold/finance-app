package com.chrispassold.data.repositories

import com.chrispassold.data.newLocalId
import com.chrispassold.data.repositories.datasources.category.CategoryLocalDataSource
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoryRepository {
    override suspend fun insertOrUpdate(category: Category) {
        if (category.id != null) {
            categoryLocalDataSource.update(
                category.copy(
                    id = newLocalId(),
                ),
            )
        } else {
            categoryLocalDataSource.insert(category)
        }
    }

    override suspend fun delete(id: String) {
        categoryLocalDataSource.delete(id)
    }

    override suspend fun getAll(): List<Category> {
        return categoryLocalDataSource.getAll()
    }

    override suspend fun get(id: String): Category? {
        return categoryLocalDataSource.get(id)
    }
}