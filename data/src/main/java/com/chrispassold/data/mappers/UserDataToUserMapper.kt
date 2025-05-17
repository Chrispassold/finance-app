package com.chrispassold.data.mappers

import com.chrispassold.core.Mapper
import com.chrispassold.data.models.UserData
import com.chrispassold.domain.models.Email
import com.chrispassold.domain.models.Password
import com.chrispassold.domain.models.User

class UserDataToUserMapper : Mapper<UserData, User> {
    override fun mapTo(from: UserData): User {
        return User(
            id = from.id,
            fullName = from.fullName,
            email = Email(from.email),
            password = Password(from.password),
            image = from.image,
        )
    }
}