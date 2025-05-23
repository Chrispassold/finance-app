package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.domain.models.Category

class CategoryToCategoryEntityMapper : Mapper<Category, CategoryEntity> {
    override fun mapTo(from: Category): CategoryEntity {
        return CategoryEntity(
            id = from.id,
            name = from.name,
            image = from.image,
            userId = from.userId,
            color = from.color,
            type = from.type,
            parentCategoryId = from.parentCategory?.id,
        )
    }
}