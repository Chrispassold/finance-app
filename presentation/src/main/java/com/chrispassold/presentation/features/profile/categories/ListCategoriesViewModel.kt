package com.chrispassold.presentation.features.profile.categories

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.TransactionType
import com.chrispassold.domain.usecases.category.ListCategoriesUseCase
import com.chrispassold.presentation.common.DefaultUiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
data class ListCategoriesUiState(
    val isLoading: Boolean = true,
    private val categories: List<Category> = emptyList(),
) {
    val expenses: List<Category> = categories.filter { it.type == TransactionType.EXPENSE }
    val incomes: List<Category> = categories.filter { it.type == TransactionType.INCOME }
}

sealed interface ListCategoriesUiEvent {
    data object OnBackClicked : ListCategoriesUiEvent
    data object OnNewClicked : ListCategoriesUiEvent
    data class OnItemClicked(val category: Category) : ListCategoriesUiEvent
}

sealed interface ListCategoriesUiEffect {
    object Idle : ListCategoriesUiEffect
    object NavigateBack : ListCategoriesUiEffect
    object NavigateNewCategory : ListCategoriesUiEffect
    data class NavigateUpdateCategory(val category: Category) : ListCategoriesUiEffect
    data class ShowSnackBar(val message: String) : ListCategoriesUiEffect
}

@HiltViewModel
class ListCategoriesViewModel @Inject constructor(
    private val listCategoriesUseCase: ListCategoriesUseCase,
) : ViewModel(), UiEventBehavior<ListCategoriesUiEvent>,
    UiEffectBehavior<ListCategoriesUiEffect> by DefaultUiEffectBehavior() {

    private val _state = MutableStateFlow(ListCategoriesUiState())
    val state = _state.onStart { list() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ListCategoriesUiState(),
    )

    override fun onEvent(event: ListCategoriesUiEvent) {
        viewModelScope.launch {
            when (event) {
                ListCategoriesUiEvent.OnBackClicked -> {
                    sendEffect(ListCategoriesUiEffect.NavigateBack)
                }

                is ListCategoriesUiEvent.OnItemClicked -> sendEffect(
                    ListCategoriesUiEffect.NavigateUpdateCategory(
                        event.category,
                    ),
                )

                ListCategoriesUiEvent.OnNewClicked -> sendEffect(ListCategoriesUiEffect.NavigateNewCategory)
            }
        }
    }

    private suspend fun list() {
        _state.value = _state.value.copy(isLoading = true)
        listCategoriesUseCase.invoke(params = ListCategoriesUseCase.Params()).onSuccess {
            _state.value = _state.value.copy(categories = it, isLoading = false)
        }.onFailure {
            _state.value = _state.value.copy(isLoading = false)
            sendEffect(ListCategoriesUiEffect.ShowSnackBar("Error listing categories: ${it.message}"))
        }
    }
}