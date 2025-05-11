package com.chrispassold.askbuddy.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chrispassold.askbuddy.data.storage.dao.BankAccountDao
import com.chrispassold.askbuddy.data.storage.dao.CategoryDao
import com.chrispassold.askbuddy.data.storage.dao.NegotiationDao
import com.chrispassold.askbuddy.data.storage.dao.UserDao
import com.chrispassold.askbuddy.data.storage.entities.BankAccount
import com.chrispassold.askbuddy.data.storage.entities.Category
import com.chrispassold.askbuddy.data.storage.entities.Negotiation
import com.chrispassold.askbuddy.data.storage.entities.User
import com.chrispassold.askbuddy.data.storage.entities.typeconverters.BigCentsConverter
import com.chrispassold.askbuddy.data.storage.entities.typeconverters.BigDecimalConverter
import com.chrispassold.askbuddy.data.storage.entities.typeconverters.NegotiationTypeConverter

// TODO: INICIALIZAR O ROOM

@Database(
    entities = [User::class, BankAccount::class, Category::class, Negotiation::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    BigDecimalConverter::class,
    BigCentsConverter::class,
    NegotiationTypeConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun negotiationDao(): NegotiationDao
}