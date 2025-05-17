package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val user: Flow<UserData?>
    suspend fun registerUser(user: UserData)
    suspend fun unregisterCurrentUser()
}