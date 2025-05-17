package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.UserData
import com.chrispassold.domain.models.User

class UserToUserDataMapper : Mapper<User, UserData> {
    override fun mapTo(from: User): UserData {
        return UserData(
            id = from.id,
            fullName = from.fullName,
            email = from.email.value,
            password = from.password.value,
            image = from.image,
        )
    }
}