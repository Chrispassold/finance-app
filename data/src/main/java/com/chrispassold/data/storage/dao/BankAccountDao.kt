package com.chrispassold.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chrispassold.data.storage.entities.BankAccount
import com.chrispassold.data.storage.entities.BankAccountWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bankAccount: BankAccount)

    @Update
    suspend fun update(bankAccount: BankAccount)

    @Delete
    suspend fun delete(bankAccount: BankAccount)

    @Transaction
    @Query("SELECT * FROM bank_account")
    fun getAllBankAccountsWithUser(): Flow<List<BankAccountWithUser>>

    @Transaction
    @Query("SELECT * FROM bank_account WHERE id = :bankAccountId")
    fun getBankAccountWithUser(bankAccountId: String): Flow<BankAccountWithUser?>
}