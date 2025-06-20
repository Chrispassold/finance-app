package com.chrispassold.data.repositories

import com.chrispassold.data.repositories.datasources.category.CategoryLocalDataSource
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoryRepository {
    override suspend fun insert(category: Category) {
        categoryLocalDataSource.insert(category)
    }

    override suspend fun update(category: Category) {
        categoryLocalDataSource.update(category)
    }

    override suspend fun delete(id: String) {
        categoryLocalDataSource.delete(id)
    }

    override suspend fun getAll(): List<Category> {
        return categoryLocalDataSource.getAll().map {
            it.apply {
                subCategories = categoryLocalDataSource.getSubCategoriesFor(it.id)
            }
        }
    }

    override suspend fun get(id: String): Category? {
        return categoryLocalDataSource.get(id)?.apply {
            subCategories = categoryLocalDataSource.getSubCategoriesFor(id)
        }
    }
}
