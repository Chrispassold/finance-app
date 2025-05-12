package com.chrispassold.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.dao.CategoryDao
import com.chrispassold.data.storage.dao.NegotiationDao
import com.chrispassold.data.storage.dao.UserDao
import com.chrispassold.data.storage.entities.BankAccount
import com.chrispassold.data.storage.entities.Category
import com.chrispassold.data.storage.entities.Negotiation
import com.chrispassold.data.storage.entities.User
import com.chrispassold.data.storage.entities.typeconverters.BigCentsConverter
import com.chrispassold.data.storage.entities.typeconverters.BigDecimalConverter
import com.chrispassold.data.storage.entities.typeconverters.LocalDateTimeConverter

// TODO: INICIALIZAR O ROOM

@Database(
    entities = [User::class, BankAccount::class, Category::class, Negotiation::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    BigDecimalConverter::class,
    BigCentsConverter::class,
    LocalDateTimeConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun negotiationDao(): NegotiationDao
}