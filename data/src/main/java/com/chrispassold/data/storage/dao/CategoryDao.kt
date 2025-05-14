package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.CategoryWithUserAndSubCategoriesEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Transaction
    @Query("SELECT * FROM categories WHERE id = :categoryId")
    suspend fun getWithUserAndSubCategories(categoryId: String): CategoryWithUserAndSubCategoriesEntity?

    @Transaction
    @Query("SELECT * FROM categories WHERE user_id = :userId")
    suspend fun getWithUserAndSubCategoriesByUserId(userId: String): List<CategoryWithUserAndSubCategoriesEntity>

    @Transaction
    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAllWithUserAndSubCategories(): List<CategoryWithUserAndSubCategoriesEntity>
}