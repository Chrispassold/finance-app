package com.chrispassold.presentation.features.profile.categories

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.domain.models.TransactionType
import com.chrispassold.domain.usecases.category.GetCategoryUseCase
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
data class DetailCategoryUiState(
    val isLoading: Boolean = false,
    val categoryName: String = "",
    val image: String? = null,
    val color: String? = null,
    val type: TransactionType = TransactionType.EXPENSE,
    val subCategories: List<SubCategory> = emptyList(),
) {
    data class SubCategory(
        val id: String?,
        val name: String,
    )
}

sealed interface DetailCategoryUiEvent {
    data object OnBack : DetailCategoryUiEvent
    data class CategoryNameChanged(val categoryName: String) : DetailCategoryUiEvent
    data class ImageChanged(val image: String) : DetailCategoryUiEvent
    data class ColorChanged(val color: String) : DetailCategoryUiEvent
    data class TypeChanged(val type: TransactionType) : DetailCategoryUiEvent
    data object ShowSubCategoryAddModal : DetailCategoryUiEvent
    data object HideSubCategoryAddModal : DetailCategoryUiEvent
    data class SubCategoryAdd(val subCategoryName: String) : DetailCategoryUiEvent

    data class SubCategoryRemove(val subCategory: DetailCategoryUiState.SubCategory) :
        DetailCategoryUiEvent

    data object Submit : DetailCategoryUiEvent
}

sealed interface DetailCategoryUiEffect {
    object Idle : DetailCategoryUiEffect
    object NavigateBack : DetailCategoryUiEffect
    data object ShowSubCategoryAddModal : DetailCategoryUiEffect
    data object HideSubCategoryAddModal : DetailCategoryUiEffect
    data class ShowSnackBar(val message: String) : DetailCategoryUiEffect
}

@HiltViewModel
class DetailCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoryUseCase: GetCategoryUseCase,
) : ViewModel(), UiEventBehavior<DetailCategoryUiEvent>,
    UiEffectBehavior<DetailCategoryUiEffect> by DefaultUiEffectBehavior() {

    private val categoryId: String? = savedStateHandle["categoryId"]

    private val _state = MutableStateFlow(DetailCategoryUiState())
    val state = _state.onStart { loadCategory() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailCategoryUiState(),
    )

    override fun onEvent(event: DetailCategoryUiEvent) {
        viewModelScope.launch {
            when (event) {
                DetailCategoryUiEvent.OnBack -> sendEffect(DetailCategoryUiEffect.NavigateBack)
                is DetailCategoryUiEvent.CategoryNameChanged -> {
                    _state.value = _state.value.copy(categoryName = event.categoryName)
                }

                is DetailCategoryUiEvent.ImageChanged -> {
                    _state.value = _state.value.copy(image = event.image)
                }

                is DetailCategoryUiEvent.ColorChanged -> {
                    _state.value = _state.value.copy(color = event.color)
                }

                is DetailCategoryUiEvent.TypeChanged -> {
                    _state.value = _state.value.copy(type = event.type)
                }

                is DetailCategoryUiEvent.SubCategoryAdd -> {
                    if (_state.value.subCategories.none { it.name == event.subCategoryName }) {
                        _state.value = _state.value.copy(
                            subCategories = _state.value.subCategories.toMutableList().apply {
                                add(
                                    DetailCategoryUiState.SubCategory(
                                        id = null,
                                        name = event.subCategoryName,
                                    ),
                                )
                            },
                        )
                    } else {
                        sendEffect(DetailCategoryUiEffect.ShowSnackBar("Sub category already exists"))
                    }
                }

                is DetailCategoryUiEvent.SubCategoryRemove -> {
                    _state.value = _state.value.copy(
                        subCategories = _state.value.subCategories.toMutableList().apply {
                            removeIf {
                                (it.id != null && it.id == event.subCategory.id) || it.name == event.subCategory.name
                            }
                        },
                    )
                }


                DetailCategoryUiEvent.ShowSubCategoryAddModal -> sendEffect(DetailCategoryUiEffect.ShowSubCategoryAddModal)
                DetailCategoryUiEvent.HideSubCategoryAddModal -> sendEffect(DetailCategoryUiEffect.HideSubCategoryAddModal)
                DetailCategoryUiEvent.Submit -> Unit //todo
            }
        }
    }

    private suspend fun loadCategory() {
        if (categoryId.isNullOrBlank()) return
        _state.value = _state.value.copy(isLoading = true)
        getCategoryUseCase.invoke(GetCategoryUseCase.Params(categoryId = categoryId))
            .onSuccess { category ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    categoryName = category.name,
                    image = category.image,
                    color = category.color,
                    type = category.type,
                    subCategories = category.subCategories.map { subCategory ->
                        DetailCategoryUiState.SubCategory(
                            id = subCategory.id,
                            name = subCategory.name,
                        )
                    },
                )
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                sendEffect(DetailCategoryUiEffect.ShowSnackBar("Error loading category: ${it.message}"))
            }
    }

    private fun create(){
        // todo
    }
}