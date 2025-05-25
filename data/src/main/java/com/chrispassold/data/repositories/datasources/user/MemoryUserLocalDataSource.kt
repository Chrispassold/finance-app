package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryUserLocalDataSource @Inject constructor() : UserLocalDataSource {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    override val user: StateFlow<User?> = _user.asStateFlow()

    override suspend fun registerUser(user: User) {
        _user.emit(user)
    }

    override suspend fun unregisterCurrentUser() {
        _user.emit(null)
    }

}