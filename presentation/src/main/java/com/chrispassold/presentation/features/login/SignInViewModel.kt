package com.chrispassold.presentation.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.usecases.login.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// todo: improve in the future
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    fun signIn(loginOption: LoginOption, onComplete: () -> Unit) {
        viewModelScope.launch {
            signInUseCase(loginOption)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

}