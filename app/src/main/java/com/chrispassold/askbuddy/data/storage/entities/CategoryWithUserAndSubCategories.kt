package com.chrispassold.askbuddy.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithUserAndSubCategories(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val user: User,
    @Relation(
        parentColumn = "parent_category_id",
        entityColumn = "id",
    ) val subCategories: List<Category> = emptyList(),
)