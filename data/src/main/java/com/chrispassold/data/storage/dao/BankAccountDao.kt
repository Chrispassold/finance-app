package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.data.storage.entities.BankAccountWithUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BankAccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bankAccountEntity: BankAccountEntity)

    @Update
    suspend fun update(bankAccountEntity: BankAccountEntity)

    @Delete
    suspend fun delete(bankAccountEntity: BankAccountEntity)

    @Transaction
    @Query("SELECT * FROM bank_account")
    fun getAllBankAccountsWithUser(): Flow<List<BankAccountWithUserEntity>>

    @Transaction
    @Query("SELECT * FROM bank_account WHERE id = :bankAccountId")
    fun getBankAccountWithUser(bankAccountId: String): Flow<BankAccountWithUserEntity?>
}