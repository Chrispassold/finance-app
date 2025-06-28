package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithSubCategoriesEntity(
    @Embedded val category: CategoryEntity,

    @Relation(
        parentColumn = "id", entityColumn = "parent_category_id"
    ) val subCategory: List<CategoryEntity>
)
