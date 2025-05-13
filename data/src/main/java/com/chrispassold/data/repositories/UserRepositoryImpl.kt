package com.chrispassold.data.repositories

import com.chrispassold.data.storage.dao.UserDao
import com.chrispassold.domain.models.User
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val userDao: UserDao
) : UserRepository {
    override fun register(user: User): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun login(
        email: String,
        password: String,
    ): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun logout(): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun currentUser(): Flow<User?> {
        TODO("Not yet implemented")
    }
}