package com.chrispassold.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.dao.CategoryDao
import com.chrispassold.data.storage.dao.NegotiationDao
import com.chrispassold.data.storage.dao.UserDao
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.NegotiationEntity
import com.chrispassold.data.storage.entities.UserEntity
import com.chrispassold.data.storage.entities.typeconverters.BigCentsConverter
import com.chrispassold.data.storage.entities.typeconverters.BigDecimalConverter
import com.chrispassold.data.storage.entities.typeconverters.DateConverter

// TODO: INICIALIZAR O ROOM

@Database(
    entities = [UserEntity::class, BankAccountEntity::class, CategoryEntity::class, NegotiationEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    BigDecimalConverter::class,
    BigCentsConverter::class,
    DateConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun negotiationDao(): NegotiationDao
}