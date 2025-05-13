package com.chrispassold.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.chrispassold.data.storage.entities.customtypes.BigCents
import java.math.BigDecimal
import java.util.Date

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
        ForeignKey(
            entity = BankAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["bank_account_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["category_id"]),
        Index(value = ["bank_account_id"]),
    ],
)
data class TransactionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "transaction_type") val type: String,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "negotiation_date") val date: Date,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "bank_account_id") val bankAccountId: String,
) {
    @ColumnInfo(name = "amount_in_cents")
    var amountInCents: BigCents = BigCents(amount)
}