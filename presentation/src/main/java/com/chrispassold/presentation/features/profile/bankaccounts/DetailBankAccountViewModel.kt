package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.core.appLogger
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.usecases.bankaccount.CreateOrUpdateBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.GetBankAccountUseCase
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehaviorImpl
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@Stable
data class DetailBankAccountUiState(
    val isLoading: Boolean = false,
    val bankAccountName: String? = null,
    val initialValue: BigDecimal = BigDecimal.ZERO,
    val hideFromBalanceCheck: Boolean = false,
    val image: String? = null,
    val type: BankAccountType = BankAccountType.CHECKING_ACCOUNT,
)

sealed interface DetailBankAccountUiEvent {
    data object OnBackClicked : DetailBankAccountUiEvent
    data class BankAccountNameChanged(val bankAccountName: String) : DetailBankAccountUiEvent
    data class InitialValueChanged(val initialValue: BigDecimal) : DetailBankAccountUiEvent
    data class HideFromBalanceCheckChanged(val hideFromBalanceCheck: Boolean) :
        DetailBankAccountUiEvent

    data class ImageChanged(val image: String) : DetailBankAccountUiEvent
    data class TypeChanged(val type: BankAccountType) : DetailBankAccountUiEvent

    data object Submit : DetailBankAccountUiEvent
}

sealed interface DetailBankAccountUiEffect {
    object Idle : DetailBankAccountUiEffect
    object NavigateBack : DetailBankAccountUiEffect
    data class ShowSnackBar(val message: String) : DetailBankAccountUiEffect
}

@HiltViewModel
class DetailBankAccountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createOrUpdateBankAccountUseCase: CreateOrUpdateBankAccountUseCase,
    private val getBankAccountUseCase: GetBankAccountUseCase,
) : ViewModel(), UiEffectBehavior<DetailBankAccountUiEffect> by UiEffectBehaviorImpl(),
    UiEventBehavior<DetailBankAccountUiEvent> {

    private val bankAccountId: String? = savedStateHandle["bankAccountId"]

    private val _state = MutableStateFlow(DetailBankAccountUiState())
    val state: StateFlow<DetailBankAccountUiState> = _state.onStart {
        bankAccountId?.let { loadBankAccount(it) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailBankAccountUiState(),
    )

    override fun onEvent(event: DetailBankAccountUiEvent) {
        when (event) {
            DetailBankAccountUiEvent.OnBackClicked -> {
                viewModelScope.launch {
                    sendEffect(DetailBankAccountUiEffect.NavigateBack)
                }
            }

            is DetailBankAccountUiEvent.BankAccountNameChanged -> {
                _state.value = _state.value.copy(bankAccountName = event.bankAccountName)
            }

            is DetailBankAccountUiEvent.InitialValueChanged -> {
                _state.value = _state.value.copy(initialValue = event.initialValue)
            }

            is DetailBankAccountUiEvent.HideFromBalanceCheckChanged -> {
                _state.value = _state.value.copy(hideFromBalanceCheck = event.hideFromBalanceCheck)
            }

            is DetailBankAccountUiEvent.ImageChanged -> {
                _state.value = _state.value.copy(image = event.image)
            }

            is DetailBankAccountUiEvent.TypeChanged -> {
                _state.value = _state.value.copy(type = event.type)
            }

            DetailBankAccountUiEvent.Submit -> createOrUpdate()
        }
    }

    private fun loadBankAccount(id: String) {
        viewModelScope.launch {
            appLogger.d("Loading banking account with id: $id")
            _state.value = _state.value.copy(isLoading = true)
            getBankAccountUseCase.invoke(GetBankAccountUseCase.Params(bankAccountId = id))
                .onSuccess { bankAccount ->
                    appLogger.d("Loaded banking account: $bankAccount")
                    _state.value = _state.value.copy(
                        bankAccountName = bankAccount.name,
                        initialValue = bankAccount.initialAmount,
                        hideFromBalanceCheck = bankAccount.hideFromBalance == true,
                        image = bankAccount.image,
                        type = bankAccount.type,
                        isLoading = false,
                    )
                }.onFailure {
                    _state.value = _state.value.copy(isLoading = false)
                    sendEffect(DetailBankAccountUiEffect.ShowSnackBar("Error loading bank account: ${it.message}"))
                }
        }
    }

    private fun createOrUpdate() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            createOrUpdateBankAccountUseCase.invoke(
                CreateOrUpdateBankAccountUseCase.Params(
                    id = bankAccountId,
                    name = state.value.bankAccountName,
                    initialAmount = state.value.initialValue,
                    hideFromBalance = state.value.hideFromBalanceCheck,
                    image = state.value.image,
                    type = state.value.type,
                ),
            ).onSuccess {
                sendEffect(DetailBankAccountUiEffect.NavigateBack)
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                sendEffect(DetailBankAccountUiEffect.ShowSnackBar("Error creating bank account: ${it.message}"))
            }
        }
    }

}