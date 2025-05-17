package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithSubCategoriesEntity(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "parent_category_id",
        entityColumn = "id",
    ) val subCategories: List<CategoryEntity> = emptyList(),
)