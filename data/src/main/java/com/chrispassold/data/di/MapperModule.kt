package com.chrispassold.data.di

import com.chrispassold.core.DataMapper
import com.chrispassold.data.mappers.UserEntityToUserMapper
import com.chrispassold.data.mappers.UserToUserEntityMapper
import com.chrispassold.data.storage.entities.UserEntity
import com.chrispassold.domain.models.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun provideUserToUserEntityMapper(
        userToUserEntityMapper: UserToUserEntityMapper,
    ): DataMapper<User, UserEntity>

    @Binds
    abstract fun provideUserEntityToUserMapper(
        userEntityToUserMapper: UserEntityToUserMapper,
    ): DataMapper<UserEntity, User>

}