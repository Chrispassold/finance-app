package com.chrispassold.domain.usecases.category

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class ListCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    class Params()

    suspend fun invoke(@Suppress("unused") params: Params = Params()): Result<List<Category>> =
        resultWithContext(Dispatchers.IO) {
            categoryRepository.getAll()
        }

}
