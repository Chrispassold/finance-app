package com.chrispassold.askbuddy.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.chrispassold.askbuddy.data.storage.entities.customtypes.BigCents
import java.math.BigDecimal

@Entity(
    tableName = "bank_account",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["user_id"]),
    ],
)
data class BankAccount(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val user: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "initial_amount") val initialAmount: BigDecimal,
    @ColumnInfo(name = "hide_from_balance") val hideFromBalance: Boolean,
    @ColumnInfo(name = "image") val image: String,
) {
    @ColumnInfo(name = "initial_amount_in_cents")
    val initialAmountInCents: BigCents = BigCents(initialAmount)
}
