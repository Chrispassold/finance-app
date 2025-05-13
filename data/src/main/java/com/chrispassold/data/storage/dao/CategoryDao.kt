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
import kotlinx.coroutines.flow.Flow

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
    fun getCategoryWithUserAndSubCategories(categoryId: String): Flow<CategoryWithUserAndSubCategoriesEntity>

    @Transaction
    @Query("SELECT * FROM categories WHERE user_id = :userId")
    fun getCategoriesWithUserAndSubCategoriesByUserId(userId: String): Flow<List<CategoryWithUserAndSubCategoriesEntity>>

    @Transaction
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategoriesWithUserAndSubCategories(): Flow<List<CategoryWithUserAndSubCategoriesEntity>>
}