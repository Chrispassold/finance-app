package com.chrispassold.domain.usecases.category

import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    data class Params(
        val category: Category,
    )

    suspend fun invoke(params: Params): Result<Unit> = runCatching {
        categoryRepository.insertOrUpdate(params.category)
    }

}