package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

internal data class BankAccountWithUserEntity(
    @Embedded val bankAccountEntity: BankAccountEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val userEntity: UserEntity,
)