package com.chrispassold.data.di

import android.content.Context
import androidx.room.Room
import com.chrispassold.data.storage.AppDatabase
import com.chrispassold.data.storage.dao.BankAccountDao
import com.chrispassold.data.storage.dao.CategoryDao
import com.chrispassold.data.storage.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "ask_buddy_database",
        ).build()
    }

    @Provides
    fun provideBankAccountDao(db: AppDatabase): BankAccountDao {
        return db.bankAccountDao()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    fun provideNegotiationDao(db: AppDatabase): TransactionDao {
        return db.negotiationDao()
    }
}