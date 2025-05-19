package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val user: Flow<User?>
    suspend fun registerUser(user: User)
    suspend fun unregisterCurrentUser()
}