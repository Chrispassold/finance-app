package com.chrispassold.presentation.features.profile.categories

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.IconTint
import com.chrispassold.domain.models.IconType
import com.chrispassold.domain.models.TransactionType
import com.chrispassold.domain.usecases.category.CreateCategoryUseCase
import com.chrispassold.domain.usecases.category.GetCategoryUseCase
import com.chrispassold.domain.usecases.category.UpdateCategoryUseCase
import com.chrispassold.presentation.common.DefaultUiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
data class DetailCategoryUiState(
    val isLoading: Boolean = false,
    val categoryName: String = "",
    val image: IconType = IconType.Generic,
    val color: IconTint = IconTint.DEFAULT,
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
    data class ImageChanged(val image: IconType) : DetailCategoryUiEvent
    data class ColorChanged(val color: IconTint) : DetailCategoryUiEvent
    data class TypeChanged(val type: TransactionType) : DetailCategoryUiEvent
    data class ShowSubCategoryModal(val subCategory: DetailCategoryUiState.SubCategory?) :
        DetailCategoryUiEvent

    data object HideSubCategoryModal : DetailCategoryUiEvent
    data class SubCategoryChange(val id: String? = null, val subCategoryName: String) :
        DetailCategoryUiEvent

    data class SubCategoryRemove(val subCategory: DetailCategoryUiState.SubCategory) :
        DetailCategoryUiEvent

    data object Submit : DetailCategoryUiEvent
}

sealed interface DetailCategoryUiEffect {
    object Idle : DetailCategoryUiEffect
    object NavigateBack : DetailCategoryUiEffect
    data class ShowSubCategoryModal(
        val subCategory: DetailCategoryUiState.SubCategory?
    ) : DetailCategoryUiEffect

    data object HideSubCategoryModal : DetailCategoryUiEffect
    class ShowSnackBar(val message: String) : DetailCategoryUiEffect
}

@HiltViewModel
class DetailCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
) : ViewModel(), UiEventBehavior<DetailCategoryUiEvent>,
    UiEffectBehavior<DetailCategoryUiEffect> by DefaultUiEffectBehavior() {

    private val categoryId: String? = savedStateHandle["categoryId"]

    private val _state = MutableStateFlow(DetailCategoryUiState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailCategoryUiState(),
    )

    init {
        viewModelScope.launch {
            loadCategory()
        }
    }

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

                is DetailCategoryUiEvent.SubCategoryChange -> {
                    if (_state.value.subCategories.none { it.name == event.subCategoryName }) {
                        _state.value = _state.value.copy(
                            subCategories = _state.value.subCategories.toMutableList().apply {
                                if (event.id != null) {
                                    removeIf { it.id == event.id }
                                }
                                add(
                                    DetailCategoryUiState.SubCategory(
                                        id = event.id,
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


                is DetailCategoryUiEvent.ShowSubCategoryModal -> sendEffect(
                    DetailCategoryUiEffect.ShowSubCategoryModal(
                        event.subCategory
                    )
                )

                DetailCategoryUiEvent.HideSubCategoryModal -> sendEffect(DetailCategoryUiEffect.HideSubCategoryModal)
                DetailCategoryUiEvent.Submit -> if (categoryId.isNullOrBlank()) create() else update(
                    categoryId
                )
            }
        }
    }

    private suspend fun loadCategory() {
        if (categoryId.isNullOrBlank()) return
        _state.value = _state.value.copy(isLoading = true)
        getCategoryUseCase.invoke(GetCategoryUseCase.Params(categoryId = categoryId)).onFailure {
            sendEffect(DetailCategoryUiEffect.ShowSnackBar("Error loading category: ${it.message}"))
            _state.value = _state.value.copy(isLoading = false)
            _state.value
        }.onSuccess { category: Category? ->
            if (category == null) return
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
        }
    }

    private fun create() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            createCategoryUseCase.invoke(
                params = CreateCategoryUseCase.Params(
                    name = _state.value.categoryName,
                    image = _state.value.image,
                    color = _state.value.color,
                    type = _state.value.type,
                    subCategories = _state.value.subCategories.map {
                        CreateCategoryUseCase.Params.SubCategory(
                            name = it.name,
                        )
                    }),
            ).onSuccess {
                sendEffect(DetailCategoryUiEffect.NavigateBack)
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                sendEffect(DetailCategoryUiEffect.ShowSnackBar("Error creating category: ${it.message}"))
            }
        }
    }

    private fun update(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            updateCategoryUseCase.invoke(
                params = UpdateCategoryUseCase.Params(
                    id = id,
                    name = _state.value.categoryName,
                    image = _state.value.image,
                    color = _state.value.color,
                    type = _state.value.type,
                    subCategories = _state.value.subCategories.map {
                        UpdateCategoryUseCase.Params.SubCategory(
                            id = it.id,
                            name = it.name,
                        )
                    })
            ).onSuccess {
                sendEffect(DetailCategoryUiEffect.NavigateBack)
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                sendEffect(DetailCategoryUiEffect.ShowSnackBar("Error updating category: ${it.message}"))
            }
        }
    }
}
