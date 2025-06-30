package com.chrispassold.domain.usecases.category

import com.chrispassold.domain.models.Category
import com.chrispassold.domain.repositories.CategoryRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetRootCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    fun invoke(): Flow<List<Category>> =
        categoryRepository.categories.flowOn(Dispatchers.IO).map { list ->
            list.filter { it.parentCategoryId == null }
        }
}
