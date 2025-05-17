package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<User?>
    suspend fun register(user: User)
    suspend fun login(email: String, password: String)
    suspend fun logout()
}