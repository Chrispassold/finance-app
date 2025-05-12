package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.BankAccount
import com.chrispassold.data.storage.entities.Negotiation
import com.chrispassold.data.storage.entities.NegotiationWithDetails // Import the new data class
import kotlinx.coroutines.flow.Flow

@Dao
interface NegotiationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(negotiation: Negotiation)

    @Update
    suspend fun update(negotiation: Negotiation)

    @Delete
    suspend fun delete(negotiation: Negotiation)

    @Transaction
    @Query("SELECT * FROM negotiations WHERE id = :negotiationId")
    fun getNegotiationWithDetails(negotiationId: String): Flow<NegotiationWithDetails>

    @Transaction
    @Query("SELECT * FROM negotiations WHERE user_id = :userId ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetailsByUserId(userId: String): Flow<List<NegotiationWithDetails>>

    @Transaction
    @Query("SELECT * FROM negotiations ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetails(): Flow<List<NegotiationWithDetails>>
}