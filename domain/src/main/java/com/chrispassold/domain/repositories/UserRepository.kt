package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.models.User

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun register(user: User)
    suspend fun loginAccount(account: LoginOption.Account)
    suspend fun loginSocialMedia(socialMediaOption: LoginOption.SocialMedia)
    suspend fun logout()
}