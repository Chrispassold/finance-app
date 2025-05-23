package com.chrispassold.domain.di

import com.chrispassold.domain.usecases.bankaccount.CreateBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.GetBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.ListBankAccountsUseCase
import com.chrispassold.domain.usecases.category.CreateCategoryUseCase
import com.chrispassold.domain.usecases.category.GetCategoryUseCase
import com.chrispassold.domain.usecases.category.ListCategoriesUseCase
import com.chrispassold.domain.usecases.login.SignInUseCase
import com.chrispassold.domain.usecases.transaction.CreateTransactionUseCase
import com.chrispassold.domain.usecases.transaction.GetTransactionUseCase
import com.chrispassold.domain.usecases.transaction.ListTransactionsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    // region: Login
    @Binds
    abstract fun bindSignInUseCase(
        signInUseCase: SignInUseCase,
    ): SignInUseCase

    // endregion: Login
    // region: BankAccounts

    @Binds
    abstract fun bindCreateBankAccountUseCase(
        createBankAccountUseCase: CreateBankAccountUseCase,
    ): CreateBankAccountUseCase

    @Binds
    abstract fun bindListBankAccountsUseCase(
        listBankAccountsUseCase: ListBankAccountsUseCase,
    ): ListBankAccountsUseCase

    @Binds
    abstract fun bindGetBankAccountUseCase(
        getBankAccountUseCase: GetBankAccountUseCase,
    ): GetBankAccountUseCase

    // endregion: BankAccounts
    // region: Category

    @Binds
    abstract fun bindCreateCategoryUseCase(
        createCategoryUseCase: CreateCategoryUseCase,
    ): CreateCategoryUseCase

    @Binds
    abstract fun bindListCategoriesUseCase(
        listCategoriesUseCase: ListCategoriesUseCase,
    ): ListCategoriesUseCase

    @Binds
    abstract fun bindGetCategoryUseCase(
        getCategoryUseCase: GetCategoryUseCase,
    ): GetCategoryUseCase

    // endregion: Category
    // region: Transactions

    @Binds
    abstract fun bindCreateTransactionUseCase(
        createTransactionUseCase: CreateTransactionUseCase,
    ): CreateTransactionUseCase

    @Binds
    abstract fun bindListTransactionsUseCase(
        listTransactionsUseCase: ListTransactionsUseCase,
    ): ListTransactionsUseCase

    @Binds
    abstract fun bindGetTransactionUseCase(
        getTransactionUseCase: GetTransactionUseCase,
    ): GetTransactionUseCase

    // endregion: Transactions
}