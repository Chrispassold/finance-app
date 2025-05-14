package com.chrispassold.data.repositories.datasources.user

import com.chrispassold.core.DataMapper
import com.chrispassold.data.storage.dao.UserDao
import com.chrispassold.data.storage.entities.UserEntity
import com.chrispassold.domain.models.User
import javax.inject.Inject

class RoomUserLocalDataSource @Inject constructor(
    private val userDao: UserDao,
    private val userToUserEntityMapper: DataMapper<User, UserEntity>,
    private val userEntityToUserMapper: DataMapper<UserEntity, User>,
) : UserLocalDataSource {
    override suspend fun insert(user: User) {
        userDao.insert(userToUserEntityMapper.mapTo(user))
    }

    override suspend fun update(user: User) {
        userDao.update(userToUserEntityMapper.mapTo(user))
    }

    override suspend fun delete(user: User) {
        userDao.delete(userToUserEntityMapper.mapTo(user))
    }

    override suspend fun get(id: String): User? {
        return userEntityToUserMapper.mapToNullable(userDao.getById(id))
    }

    override suspend fun getAll(): List<User> {
        return userEntityToUserMapper.mapToList(userDao.getAll())
    }


}