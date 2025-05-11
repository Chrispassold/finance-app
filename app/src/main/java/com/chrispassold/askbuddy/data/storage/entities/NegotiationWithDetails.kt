package com.chrispassold.askbuddy.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class NegotiationWithDetails(
    @Embedded val negotiation: Negotiation,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
    ) val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id",
    ) val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "bank_account_id",
    ) val bankAccount: BankAccount,
)