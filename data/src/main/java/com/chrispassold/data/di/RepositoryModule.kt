package com.chrispassold.data.di

import com.chrispassold.data.repositories.UserRepositoryImpl
import com.chrispassold.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Provides
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

}