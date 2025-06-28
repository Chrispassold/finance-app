package com.chrispassold.data.repositories.datasources.category

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.dao.CategoryDao
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.CategoryWithSubCategoriesEntity
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.DatabaseException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomCategoryLocalDataSource @Inject constructor(
    private val categoryDao: CategoryDao,
    private val domainToEntityMapper: Mapper<Category, CategoryEntity>,
    private val entityToDomainMapper: Mapper<CategoryEntity, Category>,
) : CategoryLocalDataSource {
    override suspend fun insert(category: Category) {
        try {
            categoryDao.insert(domainToEntityMapper.mapTo(category))
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    override suspend fun update(category: Category) {
        try {
            categoryDao.update(domainToEntityMapper.mapTo(category))
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    override suspend fun delete(id: String) {
        try {
            categoryDao.deleteById(id)
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    override suspend fun get(id: String): Category? {
        return try {
            val data = categoryDao.getCategory(id) ?: return null
            data.toDomain()
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    override fun getAllRoot(): Flow<List<Category>> {
        return try {
            categoryDao.getRootCategories().map { list ->
                list.map { category ->
                    category.toDomain()
                }
            }
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    private fun CategoryWithSubCategoriesEntity.toDomain(): Category {
        val category = this.category
        val subCategory = this.subCategory

        return entityToDomainMapper.mapTo(category).apply {
            subCategories = subCategory.map {
                entityToDomainMapper.mapTo(it)
            }
        }
    }
}
