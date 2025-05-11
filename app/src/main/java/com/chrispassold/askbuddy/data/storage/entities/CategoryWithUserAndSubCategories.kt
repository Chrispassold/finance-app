package com.chrispassold.askbuddy.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithUserAndSubCategories(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
    ) val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_category_id",
    ) val subCategories: List<Category> = emptyList(),
)