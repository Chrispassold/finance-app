package com.chrispassold.domain.usecases.category

import com.chrispassold.core.resultWithContext
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    suspend fun invoke(categoryId: String): Result<Unit> = resultWithContext(Dispatchers.IO) {
        categoryRepository.delete(categoryId)
    }

}
