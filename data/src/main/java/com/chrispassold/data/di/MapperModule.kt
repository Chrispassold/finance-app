package com.chrispassold.data.di

import com.chrispassold.core.Mapper
import com.chrispassold.data.mappers.BankAccountDataToBankAccountEntityMapper
import com.chrispassold.data.mappers.BankAccountDataToBankAccountMapper
import com.chrispassold.data.mappers.BankAccountEntityToBankAccountDataMapper
import com.chrispassold.data.mappers.BankAccountToBankAccountDataMapper
import com.chrispassold.data.mappers.UserDataToUserMapper
import com.chrispassold.data.mappers.UserToUserDataMapper
import com.chrispassold.data.models.BankAccountData
import com.chrispassold.data.models.UserData
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindUserToUserEntityMapper(
        userToUserDataMapper: UserToUserDataMapper,
    ): Mapper<User, UserData>

    @Binds
    abstract fun bindUserDataToUserMapper(
        userDataToUserMapper: UserDataToUserMapper,
    ): Mapper<UserData, User>

    @Binds
    abstract fun bindBankAccountToBankAccountDataMapper(
        bankAccountToBankAccountDataMapper: BankAccountToBankAccountDataMapper,
    ): Mapper<BankAccount, BankAccountData>

    @Binds
    abstract fun bindBankAccountDataToBankAccountMapper(
        bankAccountDataToBankAccountMapper: BankAccountDataToBankAccountMapper,
    ): Mapper<BankAccountData, BankAccount>

    @Binds
    abstract fun bindBankAccountEntityToBankAccountDataMapper(
        bankAccountEntityToBankAccountDataMapper: BankAccountEntityToBankAccountDataMapper,
    ): Mapper<BankAccountEntity, BankAccountData>

    @Binds
    abstract fun bindBankAccountDataToBankAccountEntityMapper(
        bankAccountDataToBankAccountEntityMapper: BankAccountDataToBankAccountEntityMapper,
    ): Mapper<BankAccountData, BankAccountEntity>


}