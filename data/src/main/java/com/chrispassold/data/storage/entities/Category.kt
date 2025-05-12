package com.chrispassold.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["parent_category_id"],
            onDelete = ForeignKey.SET_NULL,
        ),
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["parent_category_id"]),
    ],
)
data class Category(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "negotiation_type") val type: String,
    @ColumnInfo(name = "parent_category_id") val parentCategoryId: String?,
)
