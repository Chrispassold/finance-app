package com.chrispassold.data.mappers

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.domain.models.Category

class CategoryEntityToCategoryMapper : Mapper<CategoryEntity, Category> {
    override fun mapTo(from: CategoryEntity): Category {
        return Category(
            id = from.id,
            name = from.name,
            image = from.image,
            userId = from.userId,
            color = from.color,
            type = from.type,
            parentCategoryId = from.parentCategoryId,
        )
    }
}