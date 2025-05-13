package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

internal data class NegotiationWithDetailsEntity(
    @Embedded val negotiationEntity: NegotiationEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val userEntity: UserEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id",
    ) val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "bank_account_id",
        entityColumn = "id",
    ) val bankAccountEntity: BankAccountEntity,
)