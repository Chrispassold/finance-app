package com.chrispassold.data.di

import com.chrispassold.data.repositories.BankAccountRepositoryImpl
import com.chrispassold.data.repositories.UserRepositoryImpl
import com.chrispassold.domain.repositories.BankAccountRepository
import com.chrispassold.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun bindBankAccountRepository(
        bankAccountRepositoryImpl: BankAccountRepositoryImpl,
    ): BankAccountRepository

}