package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import java.math.BigDecimal

internal class BigDecimalConverter {

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}