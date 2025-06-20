package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.domain.models.IconType

class IconTypeConverter {
    @TypeConverter
    fun fromIconType(iconType: IconType): String {
        return iconType.name
    }

    @TypeConverter
    fun toIconType(iconTypeName: String?): IconType? {
        return iconTypeName?.let { IconType.valueOf(it) }
    }
}