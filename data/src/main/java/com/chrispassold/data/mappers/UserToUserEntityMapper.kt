package com.chrispassold.data.mappers

import com.chrispassold.core.DataMapper
import com.chrispassold.data.storage.entities.UserEntity
import com.chrispassold.domain.models.User

class UserToUserEntityMapper : DataMapper<User, UserEntity> {
    override fun mapTo(from: User): UserEntity {
        return UserEntity(
            id = from.id,
            fullName = from.fullName,
            email = from.email.value,
            password = from.password.value,
            image = from.image,
            externalId = from.externalId,
        )
    }
}