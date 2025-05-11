package com.chrispassold.askbuddy.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.askbuddy.domain.models.NegotiationType

class NegotiationTypeConverter {

    @TypeConverter
    fun fromNegotiationType(value: NegotiationType?): String? {
        return value?.databaseName
    }

    @TypeConverter
    fun toNegotiationType(value: String?): NegotiationType? {
        return value?.let { NegotiationType.fromDatabaseName(value) }
    }
}