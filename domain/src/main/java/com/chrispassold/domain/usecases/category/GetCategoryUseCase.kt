package com.chrispassold.domain.usecases.category

import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.DataNotFoundException
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    data class Params(
        val categoryId: String,
    )

    suspend fun invoke(params: Params): Result<Category> = runCatching {
        categoryRepository.get(params.categoryId)
            ?: throw DataNotFoundException("Bank account not found")
    }

}