package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryUserLocalDataSource @Inject constructor() : UserLocalDataSource {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    override suspend fun getLoggedUser(): User? = _user.value

    override suspend fun registerUser(user: User) {
        _user.emit(user)
    }

    override suspend fun unregisterCurrentUser() {
        _user.emit(null)
    }

}