package com.chrispassold.domain.models

data class Category(
    val id: String,
    val name: String,
    val image: String,
    val color: String,
    val type: TransactionType,
    val userId: String,
    val parentCategory: Category?,
    val subCategories: List<Category> = emptyList(),
)