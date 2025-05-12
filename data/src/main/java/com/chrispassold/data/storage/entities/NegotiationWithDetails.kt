package com.chrispassold.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class NegotiationWithDetails(
    @Embedded val negotiation: Negotiation,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
    ) val user: User,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id",
    ) val category: Category,
    @Relation(
        parentColumn = "bank_account_id",
        entityColumn = "id",
    ) val bankAccount: BankAccount,
)