package com.chrispassold.data.mappers

import com.chrispassold.core.DataMapper
import com.chrispassold.data.storage.entities.UserEntity
import com.chrispassold.domain.models.Email
import com.chrispassold.domain.models.Password
import com.chrispassold.domain.models.User

class UserEntityToUserMapper : DataMapper<UserEntity, User> {
    override fun mapTo(from: UserEntity): User {
        return User(
            id = from.id,
            fullName = from.fullName,
            email = Email(from.email),
            password = Password(from.password),
            image = from.image,
            externalId = from.externalId,
        )
    }

}