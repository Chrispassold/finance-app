package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteById(id: String)

    @Transaction
    @Query("SELECT * FROM categories WHERE parent_category_id IS NULL")
    suspend fun getCategories(): List<CategoryEntity>

    @Transaction
    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategory(id: String): CategoryEntity?

    @Transaction
    @Query("SELECT * FROM categories WHERE parent_category_id = :parentId")
    suspend fun getSubCategoriesFor(parentId: String): List<CategoryEntity>

}