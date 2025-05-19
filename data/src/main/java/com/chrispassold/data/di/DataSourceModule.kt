package com.chrispassold.data.di

import com.chrispassold.data.repositories.datasources.bankaccount.BankAccountLocalDataSource
import com.chrispassold.data.repositories.datasources.bankaccount.RoomBankAccountLocalDataSource
import com.chrispassold.data.repositories.datasources.category.CategoryLocalDataSource
import com.chrispassold.data.repositories.datasources.category.RoomCategoryLocalDataSource
import com.chrispassold.data.repositories.datasources.user.MemoryUserLocalDataSource
import com.chrispassold.data.repositories.datasources.user.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserLocalDataSource(
        memoryUserLocalDataSource: MemoryUserLocalDataSource,
    ): UserLocalDataSource

    @Binds
    abstract fun bindBankAccountLocalDataSource(
        roomBankAccountLocalDataSource: RoomBankAccountLocalDataSource,
    ): BankAccountLocalDataSource

    @Binds
    abstract fun bindCategoryLocalDataSource(
        roomCategoryLocalDataSource: RoomCategoryLocalDataSource,
    ): CategoryLocalDataSource

}