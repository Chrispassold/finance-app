package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BankAccountWithUser(
    @Embedded val bankAccount: BankAccount,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val user: User,
)