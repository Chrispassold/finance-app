package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class DateConverter {

    private val savedInTimeZone: TimeZone
        get() = TimeZone.getTimeZone("UTC")

    @TypeConverter
    fun fromDateToUtcTimestamp(date: Date?): Long? {
        return date?.let {
            Calendar.getInstance().apply {
                timeZone = savedInTimeZone
                time = it
            }.timeInMillis
        }
    }

    @TypeConverter
    fun fromUtcTimestampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Calendar.getInstance().apply {
                timeZone = savedInTimeZone
                timeInMillis = timestamp
            }.time // The Calendar object will automatically convert to the device's default time zone when you get its time
        }
    }
}