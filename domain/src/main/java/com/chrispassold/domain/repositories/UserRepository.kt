package com.chrispassold.domain.repositories

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun register(user: User): Flow<Unit>
    fun login(email: String, password: String): Flow<Unit>
    fun logout(): Flow<Unit>
    fun currentUser(): Flow<User?>
}