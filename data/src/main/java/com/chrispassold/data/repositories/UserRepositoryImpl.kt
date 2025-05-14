package com.chrispassold.data.repositories

import com.chrispassold.data.flowCatching
import com.chrispassold.data.repositories.datasources.user.UserLocalDataSource
import com.chrispassold.domain.models.DatabaseException
import com.chrispassold.domain.models.User
import com.chrispassold.domain.models.UserNotFoundException
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    override suspend fun register(user: User) {
        runCatching {
            userLocalDataSource.insert(user)
        }.onFailure {
            throw DatabaseException(it)
        }
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Flow<User> = flowCatching {
        val user = userLocalDataSource.get(email)
        if (user != null && user.password.value == password) {
            emit(user)
        } else {
            throw UserNotFoundException()
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun currentUser(): Flow<User?> {
        TODO("Not yet implemented")
    }
}