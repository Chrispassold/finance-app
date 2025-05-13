package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.TransactionEntity
import com.chrispassold.data.storage.entities.TransactionWithDetailsEntity // Import the new data class
import kotlinx.coroutines.flow.Flow

@Dao
interface NegotiationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionEntity: TransactionEntity)

    @Update
    suspend fun update(transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(transactionEntity: TransactionEntity)

    @Transaction
    @Query("SELECT * FROM negotiations WHERE id = :negotiationId")
    fun getNegotiationWithDetails(negotiationId: String): Flow<TransactionWithDetailsEntity>

    @Transaction
    @Query("SELECT * FROM negotiations WHERE user_id = :userId ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetailsByUserId(userId: String): Flow<List<TransactionWithDetailsEntity>>

    @Transaction
    @Query("SELECT * FROM negotiations ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetails(): Flow<List<TransactionWithDetailsEntity>>
}