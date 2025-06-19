package com.chrispassold.domain.di

import com.chrispassold.domain.repositories.BankAccountRepository
import com.chrispassold.domain.repositories.CategoryRepository
import com.chrispassold.domain.repositories.TransactionRepository
import com.chrispassold.domain.repositories.UserRepository
import com.chrispassold.domain.usecases.bankaccount.CreateBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.UpdateBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.GetBankAccountUseCase
import com.chrispassold.domain.usecases.bankaccount.ListBankAccountsUseCase
import com.chrispassold.domain.usecases.category.CreateCategoryUseCase
import com.chrispassold.domain.usecases.category.GetCategoryUseCase
import com.chrispassold.domain.usecases.category.ListCategoriesUseCase
import com.chrispassold.domain.usecases.login.SignInUseCase
import com.chrispassold.domain.usecases.login.SignUpUseCase
import com.chrispassold.domain.usecases.transaction.CreateTransactionUseCase
import com.chrispassold.domain.usecases.transaction.GetTransactionUseCase
import com.chrispassold.domain.usecases.transaction.ListTransactionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // region: Login
    @Provides
    fun provideSignInUseCase(
        userRepository: UserRepository,
    ): SignInUseCase {
        return SignInUseCase(
            userRepository = userRepository,
        )
    }

    @Provides
    fun provideSignUpUseCase(
        userRepository: UserRepository,
    ): SignUpUseCase {
        return SignUpUseCase(
            userRepository = userRepository,
        )
    }

    // endregion: Login
    // region: BankAccounts

    @Provides
    fun provideCreateBankAccountUseCase(
        bankAccountRepository: BankAccountRepository,
        userRepository: UserRepository,
    ): CreateBankAccountUseCase = CreateBankAccountUseCase(
        bankAccountRepository = bankAccountRepository,
        userRepository = userRepository,
    )

    @Provides
    fun provideUpdateBankAccountUseCase(
        bankAccountRepository: BankAccountRepository,
        userRepository: UserRepository,
        getBankAccountUseCase: GetBankAccountUseCase,
    ): UpdateBankAccountUseCase {
        return UpdateBankAccountUseCase(
            bankAccountRepository = bankAccountRepository,
            userRepository = userRepository,
            getBankAccountUseCase = getBankAccountUseCase,
        )
    }

    @Provides
    fun provideListBankAccountsUseCase(
        bankAccountRepository: BankAccountRepository,
    ): ListBankAccountsUseCase {
        return ListBankAccountsUseCase(
            bankAccountRepository = bankAccountRepository,
        )
    }

    @Provides
    fun provideGetBankAccountUseCase(
        bankAccountRepository: BankAccountRepository,
    ): GetBankAccountUseCase {
        return GetBankAccountUseCase(
            bankAccountRepository = bankAccountRepository,
        )
    }

    // endregion: BankAccounts
    // region: Category

    @Provides
    fun provideCreateCategoryUseCase(
        categoryRepository: CategoryRepository,
    ): CreateCategoryUseCase {
        return CreateCategoryUseCase(
            categoryRepository = categoryRepository,
        )
    }

    @Provides
    fun provideListCategoriesUseCase(
        categoryRepository: CategoryRepository,
    ): ListCategoriesUseCase {
        return ListCategoriesUseCase(
            categoryRepository = categoryRepository,
        )
    }

    @Provides
    fun provideGetCategoryUseCase(
        categoryRepository: CategoryRepository,
    ): GetCategoryUseCase {
        return GetCategoryUseCase(
            categoryRepository = categoryRepository,
        )
    }

    // endregion: Category
    // region: Transactions

    @Provides
    fun provideCreateTransactionUseCase(
        transactionRepository: TransactionRepository,
    ): CreateTransactionUseCase {
        return CreateTransactionUseCase(
            transactionRepository = transactionRepository,
        )
    }

    @Provides
    fun provideListTransactionsUseCase(
        transactionRepository: TransactionRepository,
    ): ListTransactionsUseCase {
        return ListTransactionsUseCase(
            transactionRepository = transactionRepository,
        )
    }

    @Provides
    fun provideGetTransactionUseCase(
        transactionRepository: TransactionRepository,
    ): GetTransactionUseCase {
        return GetTransactionUseCase(
            transactionRepository = transactionRepository,
        )
    }

    // endregion: Transactions
}