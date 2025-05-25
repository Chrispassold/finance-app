package com.chrispassold.domain.usecases.login

import com.chrispassold.core.appLogger
import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(loginOption: LoginOption): Result<Unit> = runCatching {
        withContext(Dispatchers.Default) {
            appLogger.d("Sign in with $loginOption")
            delay(2000)
            when (loginOption) {
                is LoginOption.Account -> {
                    userRepository.loginAccount(loginOption)
                }

                is LoginOption.SocialMedia -> {
                    userRepository.loginSocialMedia(loginOption)
                }
            }
        }
    }
}