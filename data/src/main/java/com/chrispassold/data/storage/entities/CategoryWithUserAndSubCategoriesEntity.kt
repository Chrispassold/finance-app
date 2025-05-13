package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithUserAndSubCategoriesEntity(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val userEntity: UserEntity,
    @Relation(
        parentColumn = "parent_category_id",
        entityColumn = "id",
    ) val subCategories: List<CategoryEntity> = emptyList(),
)