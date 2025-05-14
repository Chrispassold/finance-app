package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.domain.models.User

interface UserLocalDataSource {
    suspend fun insert(user: User)
    suspend fun update(user: User)
    suspend fun delete(user: User)
    suspend fun get(id: String): User?
    suspend fun getAll(): List<User>
}