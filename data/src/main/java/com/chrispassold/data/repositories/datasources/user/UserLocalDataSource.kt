package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.domain.models.User

interface UserLocalDataSource {
    suspend fun getLoggedUser(): User?
    suspend fun registerUser(user: User)
    suspend fun unregisterCurrentUser()
}