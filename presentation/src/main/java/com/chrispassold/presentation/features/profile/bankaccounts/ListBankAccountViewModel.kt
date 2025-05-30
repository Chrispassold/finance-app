package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.core.appLogger
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.Money
import com.chrispassold.domain.usecases.bankaccount.ListBankAccountsUseCase
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehaviorImpl
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
data class ListBankAccountUiState(
    val bankAccounts: List<BankAccount> = emptyList(),
    val isLoading: Boolean = false,
) {
    val totalAmount: Money = Money(
        bankAccounts.sumOf {
            appLogger.i("Total amount: ${it.initialAmount.amount}")
            it.initialAmount.amount
        },
    )
}

sealed interface ListBankAccountUiEvent {
    data object OnBackClicked : ListBankAccountUiEvent
    data class OnBankAccountClicked(val bankAccount: BankAccount) : ListBankAccountUiEvent
    data object OnNewBankAccountClicked : ListBankAccountUiEvent
}

sealed interface ListBankAccountUiEffect {
    object Idle : ListBankAccountUiEffect
    object NavigateBack : ListBankAccountUiEffect
    object NavigateNewBankAccount : ListBankAccountUiEffect
    data class NavigateUpdateBankAccount(val bankAccount: BankAccount) : ListBankAccountUiEffect
    data class ShowSnackBar(val message: String) : ListBankAccountUiEffect
}

@HiltViewModel
class ListBankAccountViewModel @Inject constructor(
    private val listBankAccountsUseCase: ListBankAccountsUseCase,
) : ViewModel(), UiEffectBehavior<ListBankAccountUiEffect> by UiEffectBehaviorImpl(),
    UiEventBehavior<ListBankAccountUiEvent> {

    private val _state = MutableStateFlow(ListBankAccountUiState())
    val state = _state.onStart {
        list()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ListBankAccountUiState(),
    )

    override fun onEvent(event: ListBankAccountUiEvent) {
        when (event) {
            ListBankAccountUiEvent.OnBackClicked -> {
                viewModelScope.launch {
                    sendEffect(ListBankAccountUiEffect.NavigateBack)
                }
            }

            is ListBankAccountUiEvent.OnBankAccountClicked -> {
                viewModelScope.launch {
                    sendEffect(ListBankAccountUiEffect.NavigateUpdateBankAccount(event.bankAccount))
                }
            }

            ListBankAccountUiEvent.OnNewBankAccountClicked -> {
                viewModelScope.launch {
                    sendEffect(ListBankAccountUiEffect.NavigateNewBankAccount)
                }
            }
        }
    }

    private fun list() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            listBankAccountsUseCase.invoke(
                ListBankAccountsUseCase.Params(),
            ).onSuccess {
                _state.value = _state.value.copy(bankAccounts = it, isLoading = false)
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                sendEffect(ListBankAccountUiEffect.ShowSnackBar("Error listing bank accounts: ${it.message}"))
            }
        }
    }

}