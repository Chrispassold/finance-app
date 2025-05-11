package com.chrispassold.askbuddy.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.askbuddy.data.storage.entities.Category
import com.chrispassold.askbuddy.data.storage.entities.CategoryWithUserAndSubCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Transaction
    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategoryWithUserAndSubCategories(categoryId: String): Flow<CategoryWithUserAndSubCategories>

    @Transaction
    @Query("SELECT * FROM categories WHERE user_id = :userId")
    fun getCategoriesWithUserAndSubCategoriesByUserId(userId: String): Flow<List<CategoryWithUserAndSubCategories>>

    @Transaction
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategoriesWithUserAndSubCategories(): Flow<List<CategoryWithUserAndSubCategories>>
}