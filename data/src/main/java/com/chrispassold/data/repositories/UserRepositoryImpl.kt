package com.chrispassold.data.repositories

import com.chrispassold.data.repositories.datasources.user.UserLocalDataSource
import com.chrispassold.domain.models.Email
import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.models.Password
import com.chrispassold.domain.models.User
import com.chrispassold.domain.repositories.UserRepository
import javax.inject.Inject

// todo: Improve
class UserRepositoryImpl @Inject constructor(
    val userLocalDataSource: UserLocalDataSource,
) : UserRepository {

    private val testUser by lazy {
        User(
            id = "b9d3587b-66cd-4618-bc32-63191cf7f290",
            email = Email("chris@h.com"),
            password = Password("123456789"),
            fullName = "Chris Passold",
        )
    }

    override suspend fun getCurrentUser(): User? {
        return userLocalDataSource.getLoggedUser()
    }

    override suspend fun register(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun loginAccount(account: LoginOption.Account) {
        userLocalDataSource.registerUser(testUser)
    }

    override suspend fun loginSocialMedia(socialMediaOption: LoginOption.SocialMedia) {
        userLocalDataSource.registerUser(testUser)
    }

    override suspend fun logout() {
        userLocalDataSource.unregisterCurrentUser()
    }

}