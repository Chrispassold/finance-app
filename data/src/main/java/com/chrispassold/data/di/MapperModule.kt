package com.chrispassold.data.di

import com.chrispassold.core.common.Mapper
import com.chrispassold.data.mappers.BankAccountEntityToBankAccountMapper
import com.chrispassold.data.mappers.BankAccountToBankAccountEntityMapper
import com.chrispassold.data.mappers.CategoryToCategoryEntityMapper
import com.chrispassold.data.mappers.CategoryEntityToCategoryMapper
import com.chrispassold.data.mappers.TransactionToTransactionEntityMapper
import com.chrispassold.data.mappers.TransactionWithDetailsEntityToTransactionMapper
import com.chrispassold.data.storage.entities.BankAccountEntity
import com.chrispassold.data.storage.entities.CategoryEntity
import com.chrispassold.data.storage.entities.TransactionEntity
import com.chrispassold.data.storage.entities.TransactionWithDetailsEntity
import com.chrispassold.domain.models.BankAccount
import com.chrispassold.domain.models.Category
import com.chrispassold.domain.models.Transaction
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindBankAccountToBankAccountEntityMapper(
        bankAccountToBankAccountEntityMapper: BankAccountToBankAccountEntityMapper,
    ): Mapper<BankAccount, BankAccountEntity>

    @Binds
    abstract fun bindBankAccountEntityToBankAccountMapper(
        bankAccountEntityToBankAccountMapper: BankAccountEntityToBankAccountMapper,
    ): Mapper<BankAccountEntity, BankAccount>

    @Binds
    abstract fun bindCategoryToCategoryEntityMapper(
        categoryToCategoryEntityMapper: CategoryToCategoryEntityMapper,
    ): Mapper<Category, CategoryEntity>

    @Binds
    abstract fun bindCategoryEntityToCategoryMapper(
        categoryEntityToCategoryMapper: CategoryEntityToCategoryMapper,
    ): Mapper<CategoryEntity, Category>

    @Binds
    abstract fun bindTransactionToTransactionEntityMapper(
        transactionToTransactionEntityMapper: TransactionToTransactionEntityMapper,
    ): Mapper<Transaction, TransactionEntity>

    @Binds
    abstract fun bindTransactionWithDetailsEntityToTransactionMapper(
        transactionWithDetailsEntityToTransactionMapper: TransactionWithDetailsEntityToTransactionMapper,
    ): Mapper<TransactionWithDetailsEntity, Transaction>

}