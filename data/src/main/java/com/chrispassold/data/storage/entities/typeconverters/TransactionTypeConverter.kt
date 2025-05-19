package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.domain.models.TransactionType

internal class TransactionTypeConverter {

    @TypeConverter
    fun fromTransactionType(type: TransactionType?): String? {
        return type?.databaseName
    }

    @TypeConverter
    fun toTransactionType(name: String?): TransactionType? {
        return name?.let { TransactionType.fromDatabaseName(it) }
    }
}