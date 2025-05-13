package com.chrispassold.data.di

import com.chrispassold.data.repositories.UserRepositoryImpl
import com.chrispassold.data.storage.dao.UserDao
import com.chrispassold.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    fun provideUserRepository(
        userDao: UserDao,
    ): UserRepository = UserRepositoryImpl(
        userDao = userDao,
    )

}