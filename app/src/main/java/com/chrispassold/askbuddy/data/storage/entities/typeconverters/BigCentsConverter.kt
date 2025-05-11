package com.chrispassold.askbuddy.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.askbuddy.data.storage.entities.customtypes.BigCents

class BigCentsConverter {

    @TypeConverter
    fun fromBigCents(value: BigCents?): Long? {
        return value?.toLong()
    }

    @TypeConverter
    fun toBigCents(value: Long?): BigCents? {
        return value?.let { BigCents(it) }
    }
}