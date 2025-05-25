package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<User?>
    suspend fun register(user: User)
    suspend fun loginAccount(account: LoginOption.Account)
    suspend fun loginSocialMedia(socialMediaOption: LoginOption.SocialMedia)
    suspend fun logout()
}