package com.chrispassold.domain.usecases.category

import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject

class ListCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    class Params()

    suspend fun invoke(@Suppress("unused") params: Params): Result<List<Category>> = runCatching {
        categoryRepository.getAll()
    }

}