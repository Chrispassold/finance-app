package com.chrispassold.data.repositories.datasources.category

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.dao.CategoryDao
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.CategoryWithDetailsEntity
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.DatabaseException
import javax.inject.Inject

class RoomCategoryLocalDataSource @Inject constructor(
    private val categoryDao: CategoryDao,
    private val domainToEntityMapper: Mapper<Category, CategoryEntity>,
    private val entityToDomainMapper: Mapper<CategoryWithDetailsEntity, Category>,
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
            entityToDomainMapper.mapToNullable(
                categoryDao.getCategory(id)?.apply {
                    subCategories = categoryDao.getSubCategoriesFor(this.entityCategory.id)
                },
            )
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }

    override suspend fun getAll(): List<Category> {
        return try {
            entityToDomainMapper.mapToList(
                categoryDao.getCategories().map { categoryEntity ->
                    categoryEntity.apply {
                        subCategories = categoryDao.getSubCategoriesFor(this.entityCategory.id)
                    }
                },
            )
        } catch (e: Throwable) {
            throw DatabaseException(e)
        }
    }
}