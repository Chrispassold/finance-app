package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.domain.models.BankAccountType

private const val CHECKING_ACCOUNT_DATABASE_NAME = "checking"
private const val SAVING_ACCOUNT_DATABASE_NAME = "saving"

internal class BankAccountTypeConverter {
    @TypeConverter
    fun fromBankAccountType(type: BankAccountType?): String? {
        return when (type) {
            BankAccountType.CHECKING_ACCOUNT -> CHECKING_ACCOUNT_DATABASE_NAME
            BankAccountType.SAVING_ACCOUNT -> SAVING_ACCOUNT_DATABASE_NAME
            null -> null
        }
    }

    @TypeConverter
    fun toBankAccountType(name: String?): BankAccountType? {
        return when (name) {
            CHECKING_ACCOUNT_DATABASE_NAME -> BankAccountType.CHECKING_ACCOUNT
            SAVING_ACCOUNT_DATABASE_NAME -> BankAccountType.SAVING_ACCOUNT
            else -> null
        }
    }
}