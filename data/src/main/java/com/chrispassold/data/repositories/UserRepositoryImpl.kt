package com.chrispassold.data.repositories

import com.chrispassold.data.repositories.datasources.user.UserLocalDataSource
import com.chrispassold.domain.models.Email
import com.chrispassold.domain.models.Password
import com.chrispassold.domain.models.User
import com.chrispassold.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    override val currentUser: Flow<User?> = userLocalDataSource.user

    private val testUser = User(
        id = "b9d3587b-66cd-4618-bc32-63191cf7f290",
        email = Email("chris@h.com"),
        password = Password("123456"),
        fullName = "Chris Passold",
    )

    override suspend fun register(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        email: String,
        password: String,
    ) {
        userLocalDataSource.registerUser(testUser)
    }

    override suspend fun logout() {
        userLocalDataSource.unregisterCurrentUser()
    }

}