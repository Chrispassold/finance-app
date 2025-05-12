package com.chrispassold.askbuddy.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTimeToUtcTimestamp(localDateTime: LocalDateTime?): Long? {
        return localDateTime?.let {
            // Convert the LocalDateTime (assumed to be in the local timezone)
            // to an Instant in UTC.
            val localZoneId = ZoneId.systemDefault() // Get the device's default timezone
            val instant = it.atZone(localZoneId).toInstant()

            // Convert the Instant to a timestamp (milliseconds since epoch)
            instant.toEpochMilli()
        }
    }

    @TypeConverter
    fun fromUtcTimestampToLocalDateTime(timestamp: Long?): LocalDateTime? {
        return timestamp?.let {
            // Convert the timestamp (milliseconds since epoch in UTC) to an Instant
            val instant = Instant.ofEpochMilli(it)

            // Convert the Instant (in UTC) to LocalDateTime in the device's local timezone
            val localZoneId = ZoneId.systemDefault() // Get the device's default timezone
            instant.atZone(localZoneId).toLocalDateTime()
        }
    }
}