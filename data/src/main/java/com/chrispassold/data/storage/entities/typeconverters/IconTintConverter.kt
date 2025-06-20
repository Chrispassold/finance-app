package com.chrispassold.data.storage.entities.typeconverters

import androidx.room.TypeConverter
import com.chrispassold.domain.models.IconTint

internal class IconTintConverter {

    @TypeConverter
    fun fromIconTint(tint: IconTint?): String? {
        return tint?.name ?: IconTint.DEFAULT.name
    }

    @TypeConverter
    fun toIconTint(name: String?): IconTint {
        return name?.let { IconTint.valueOf(it) } ?: IconTint.DEFAULT
    }

}