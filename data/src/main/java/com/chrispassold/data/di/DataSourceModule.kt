package com.chrispassold.data.di

import com.chrispassold.data.repositories.datasources.user.RoomUserLocalDataSource
import com.chrispassold.data.repositories.datasources.user.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideUserLocalDataSource(
        roomUserLocalDataSource: RoomUserLocalDataSource,
    ): UserLocalDataSource

}