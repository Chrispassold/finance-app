package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.storage.entities.CategoryWithDetailsEntity
import com.chrispassold.domain.models.Category

class CategoryWithDetailsEntityToCategoryMapper : Mapper<CategoryWithDetailsEntity, Category> {
    override fun mapTo(from: CategoryWithDetailsEntity): Category {
        return Category(
            id = from.entityCategory.id,
            name = from.entityCategory.name,
            image = from.entityCategory.image,
            userId = from.entityCategory.userId,
            color = from.entityCategory.color,
            type = from.entityCategory.type,
            parentCategory = from.parentCategory?.let { parentCategory ->
                mapTo(parentCategory)
            },
            subCategories = from.subCategories.map { subCategory ->
                mapTo(subCategory)
            },
        )
    }
}