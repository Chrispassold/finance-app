package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chrispassold.data.storage.entities.BankAccountEntity

@Dao
interface BankAccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bankAccountEntity: BankAccountEntity)

    @Update
    suspend fun update(bankAccountEntity: BankAccountEntity)

    @Query("DELETE FROM bank_account WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM bank_account WHERE id = :id")
    suspend fun get(id: String): BankAccountEntity?

    @Query("SELECT * FROM bank_account ORDER BY name ASC")
    suspend fun getAll(): List<BankAccountEntity>

}