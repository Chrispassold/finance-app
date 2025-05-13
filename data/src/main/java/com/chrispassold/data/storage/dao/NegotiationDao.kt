package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.NegotiationEntity
import com.chrispassold.data.storage.entities.NegotiationWithDetailsEntity // Import the new data class
import kotlinx.coroutines.flow.Flow

@Dao
internal interface NegotiationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(negotiationEntity: NegotiationEntity)

    @Update
    suspend fun update(negotiationEntity: NegotiationEntity)

    @Delete
    suspend fun delete(negotiationEntity: NegotiationEntity)

    @Transaction
    @Query("SELECT * FROM negotiations WHERE id = :negotiationId")
    fun getNegotiationWithDetails(negotiationId: String): Flow<NegotiationWithDetailsEntity>

    @Transaction
    @Query("SELECT * FROM negotiations WHERE user_id = :userId ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetailsByUserId(userId: String): Flow<List<NegotiationWithDetailsEntity>>

    @Transaction
    @Query("SELECT * FROM negotiations ORDER BY negotiation_date DESC")
    fun getAllNegotiationsWithDetails(): Flow<List<NegotiationWithDetailsEntity>>
}