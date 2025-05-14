package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun register(user: User)
    suspend fun login(email: String, password: String): Flow<User>
    suspend fun logout()
    suspend fun currentUser(): Flow<User?>
}