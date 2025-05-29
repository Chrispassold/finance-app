package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.usecases.bankaccount.CreateBankAccountUseCase
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehaviorImpl
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@Stable
data class DetailBankAccountUiState(
    val bankAccountName: String? = null,
    val initialValue: BigDecimal? = null,
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
    private val createBankAccountUseCase: CreateBankAccountUseCase,
) : ViewModel(), UiEffectBehavior<DetailBankAccountUiEffect> by UiEffectBehaviorImpl(),
    UiEventBehavior<DetailBankAccountUiEvent> {

    private val _state = MutableStateFlow(DetailBankAccountUiState())
    val state: StateFlow<DetailBankAccountUiState> = _state.stateIn(
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

            DetailBankAccountUiEvent.Submit -> create()
        }
    }

    private fun create() {
        viewModelScope.launch {
            createBankAccountUseCase.invoke(
                CreateBankAccountUseCase.Params(
                    name = state.value.bankAccountName,
                    initialAmount = state.value.initialValue,
                    hideFromBalance = state.value.hideFromBalanceCheck,
                    image = state.value.image,
                    type = state.value.type,
                ),
            ).onSuccess {
                sendEffect(DetailBankAccountUiEffect.ShowSnackBar("Success"))
            }.onFailure {
                sendEffect(DetailBankAccountUiEffect.ShowSnackBar("Error creating bank account: ${it.message}"))
            }
        }
    }

}