package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

data class CategoryWithDetailsEntity(
    @Embedded val entityCategory: CategoryEntity,
    @Relation(
        parentColumn = "parent_category_id",
        entityColumn = "id",
    ) val parentCategory: CategoryWithDetailsEntity?,
) {
    @Ignore
    var subCategories: List<CategoryWithDetailsEntity> = emptyList()
}