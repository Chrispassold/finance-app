package com.chrispassold.domain.usecases.login

import com.chrispassold.domain.repositories.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    data class Params(
        val email: String,
        val password: String,
    )

    suspend operator fun invoke(params: Params): Result<Unit> = runCatching {
        userRepository.login(params.email, params.password)
    }
}