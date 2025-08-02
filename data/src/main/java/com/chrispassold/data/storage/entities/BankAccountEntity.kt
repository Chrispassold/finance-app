package com.chrispassold.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.chrispassold.data.storage.entities.customtypes.BigCents
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.models.IconType
import java.math.BigDecimal

@Entity(
    tableName = "bank_account",
    indices = [
        Index(value = ["user_id"]),
    ],
)
data class BankAccountEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "initial_amount") val initialAmount: BigDecimal,
    @ColumnInfo(name = "hide_from_balance") val hideFromBalance: Boolean,
    @ColumnInfo(name = "type") val type: BankAccountType,
    @ColumnInfo(name = "image") val image: IconType,
) {
    @ColumnInfo(name = "initial_amount_in_cents")
    var initialAmountInCents: BigCents = BigCents(initialAmount)
}
