package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.data.models.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

@Singleton
class MemoryUserLocalDataSource : UserLocalDataSource {
    private val _user: MutableStateFlow<UserData?> = MutableStateFlow(null)
    override val user: StateFlow<UserData?> = _user.asStateFlow()

    override suspend fun registerUser(user: UserData) {
        _user.emit(user)
    }

    override suspend fun unregisterCurrentUser() {
        _user.emit(null)
    }

}