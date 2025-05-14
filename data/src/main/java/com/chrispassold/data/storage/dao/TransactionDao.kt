package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.TransactionEntity
import com.chrispassold.data.storage.entities.TransactionWithDetailsEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionEntity: TransactionEntity)

    @Update
    suspend fun update(transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(transactionEntity: TransactionEntity)

    @Transaction
    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    suspend fun getWithDetails(transactionId: String): TransactionWithDetailsEntity

    @Transaction
    @Query("SELECT * FROM transactions WHERE user_id = :userId ORDER BY transaction_date DESC")
    suspend fun getAllWithDetailsByUserId(userId: String): List<TransactionWithDetailsEntity>

    @Transaction
    @Query("SELECT * FROM transactions ORDER BY transaction_date DESC")
    suspend fun getAllWithDetails(): List<TransactionWithDetailsEntity>
}