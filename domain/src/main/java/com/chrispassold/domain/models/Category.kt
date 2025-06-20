package com.chrispassold.domain.models

data class Category(
    val id: String,
    val name: String,
    val image: IconType = IconType.Generic,
    val type: TransactionType,
    val userId: String,
    val color: IconTint = IconTint.DEFAULT,
    val parentCategoryId: String?,
) {
    var subCategories: List<Category> = emptyList()
}
