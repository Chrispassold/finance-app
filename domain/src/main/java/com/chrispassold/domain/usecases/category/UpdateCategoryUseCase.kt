package com.chrispassold.domain.usecases.category

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.models.IconTint
import com.chrispassold.domain.models.IconType
import com.chrispassold.domain.models.TransactionType
import com.chrispassold.domain.repositories.CategoryRepository
import com.chrispassold.domain.repositories.UserRepository
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class UpdateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
) {

    data class Params(
        val id: String,
        val name: String,
        val image: IconType,
        val color: IconTint,
        val type: TransactionType,
        val subCategories: List<SubCategory> = emptyList(),
    ) {
        data class SubCategory(
            val id: String?,
            val name: String,
        )
    }

    suspend fun invoke(params: Params): Result<Unit> = resultWithContext(Dispatchers.IO) {
        require(params.id.isNotBlank()) { "Id cannot be empty" }
        require(params.name.isNotBlank()) { "Name cannot be empty" }
        val categoryEntity = categoryRepository.get(params.id) ?: error("Category not found")
        val user = userRepository.getCurrentUser() ?: error("User not logged in")
        // delete subcategory
        categoryEntity.subCategories.forEach { subCategoryEntity ->
            if (params.subCategories.none { it.id == subCategoryEntity.id }) {
                categoryRepository.delete(subCategoryEntity.id)
            }
        }
        // update subcategory
        params.subCategories.forEach { subCategory ->
            val subCategories = categoryEntity.copy(
                id = subCategory.id ?: UUID.randomUUID().toString(),
                name = subCategory.name,
                parentCategoryId = params.id,
            )
            if (subCategory.id.isNullOrBlank()) {
                categoryRepository.insert(subCategories)
            } else {
                categoryRepository.update(subCategories)
            }
        }
        // update category
        val categoryCopy = categoryEntity.copy(
            name = params.name,
            image = params.image,
            color = params.color,
            type = params.type,
            userId = user.id,
        )
        categoryRepository.update(categoryCopy)
    }

}
