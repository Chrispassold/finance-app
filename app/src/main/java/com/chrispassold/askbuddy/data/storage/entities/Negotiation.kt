package com.chrispassold.askbuddy.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.chrispassold.askbuddy.data.storage.entities.customtypes.BigCents
import com.chrispassold.askbuddy.domain.models.NegotiationType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Date

@Entity(
    tableName = "negotiations",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
        ForeignKey(
            entity = BankAccount::class,
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
data class Negotiation(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "negotiation_type") val type: NegotiationType,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "negotiation_date") val date: LocalDateTime,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "bank_account_id") val bankAccountId: String,
) {
    @ColumnInfo(name = "amount_in_cents")
    var amountInCents: BigCents = BigCents(amount)
}