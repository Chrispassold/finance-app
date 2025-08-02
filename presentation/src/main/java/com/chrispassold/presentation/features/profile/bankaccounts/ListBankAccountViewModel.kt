package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.core.appLogger
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.usecases.bankaccount.GetAllBankAccountsUseCase
import com.chrispassold.presentation.common.DefaultUiEffectBehavior
import com.chrispassold.presentation.common.UiEffectBehavior
import com.chrispassold.presentation.common.UiEventBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigDecimal
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
data class ListBankAccountUiState(
    val bankAccounts: List<BankAccount> = emptyList(),
    val isLoading: Boolean = false,
) {
    val totalAmount: BigDecimal = bankAccounts.sumOf {
        appLogger.i("Total amount: ${it.initialAmount}")
        it.initialAmount
    }
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
    getAllBankAccountsUseCase: GetAllBankAccountsUseCase,
) : ViewModel(), UiEffectBehavior<ListBankAccountUiEffect> by DefaultUiEffectBehavior(),
    UiEventBehavior<ListBankAccountUiEvent> {

    private val _state = MutableStateFlow(ListBankAccountUiState())
    val state = getAllBankAccountsUseCase.invoke().map {
            _state.value = _state.value.copy(bankAccounts = it, isLoading = false)
            _state.value
        }
        .catch {
            _state.value = _state.value.copy(isLoading = false)
            sendEffect(ListBankAccountUiEffect.ShowSnackBar("Error listing bank accounts: ${it.message}"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListBankAccountUiState(isLoading = true),
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
}
