package com.chrispassold.presentation.features.profile

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chrispassold.presentation.features.login.navigateToLogin
import com.chrispassold.presentation.features.profile.bankaccounts.DetailBankAccountScreen
import com.chrispassold.presentation.features.profile.bankaccounts.DetailBankAccountUiEffect
import com.chrispassold.presentation.features.profile.bankaccounts.DetailBankAccountViewModel
import com.chrispassold.presentation.features.profile.bankaccounts.ListBankAccountUiEffect
import com.chrispassold.presentation.features.profile.bankaccounts.ListBankAccountViewModel
import com.chrispassold.presentation.features.profile.bankaccounts.ListBankAccountsScreen
import com.chrispassold.presentation.features.profile.categories.DetailCategoryScreen
import com.chrispassold.presentation.features.profile.categories.DetailCategoryUiEffect
import com.chrispassold.presentation.features.profile.categories.DetailCategoryViewModel
import com.chrispassold.presentation.features.profile.categories.ListCategoriesScreen
import com.chrispassold.presentation.features.profile.categories.ListCategoriesUiEffect
import com.chrispassold.presentation.features.profile.categories.ListCategoriesViewModel
import com.chrispassold.presentation.features.profile.personalinformation.PersonalInformationScreen
import kotlinx.serialization.Serializable

@Serializable
private data object ProfileEntryDestination

@Serializable
private data object ProfileHomeDestination

@Serializable
private data object BankAccountsDestination {
    @Serializable
    data object List

    @Serializable
    data class Detail(val bankAccountId: String? = null)
}

@Serializable
private data object CategoriesDestination {
    @Serializable
    data object List

    @Serializable
    data class Detail(val categoryId: String? = null)
}

@Serializable
private data object PersonalInformationDestination

fun NavGraphBuilder.profile(navController: NavController) {
    navigation<ProfileEntryDestination>(startDestination = ProfileHomeDestination) {
        profileHome(navController)
        bankAccounts(navController)
        categories(navController)
        personalInformation(navController)
    }
}

private fun NavGraphBuilder.profileHome(navController: NavController) {
    composable<ProfileHomeDestination> {
        ProfileScreen(
            onNavigateToBankAccounts = {
                navController.navigateToBankAccounts()
            },
            onNavigateToCategories = {
                navController.navigateToCategories()
            },
            onNavigateToPersonalInformation = {
                navController.navigateToPersonalInformation()
            },
            onNavigateToCreditCards = {
                TODO()
            },
            onExitApp = {
                navController.navigateToLogin()
            },
        )
    }
}


private fun NavGraphBuilder.bankAccounts(navController: NavController) {
    navigation<BankAccountsDestination>(startDestination = BankAccountsDestination.List) {
        composable<BankAccountsDestination.List> {
            val viewModel = hiltViewModel<ListBankAccountViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.effect.collectAsState(ListBankAccountUiEffect.Idle)

            LaunchedEffect(effect) {
                when (effect) {
                    ListBankAccountUiEffect.NavigateBack -> {
                        navController.popBackStack()
                    }

                    ListBankAccountUiEffect.NavigateNewBankAccount -> {
                        navController.navigate(BankAccountsDestination.Detail(null))
                    }

                    is ListBankAccountUiEffect.NavigateUpdateBankAccount -> {
                        navController.navigate(BankAccountsDestination.Detail((effect as ListBankAccountUiEffect.NavigateUpdateBankAccount).bankAccount.id))
                    }

                    else -> Unit
                }
            }

            ListBankAccountsScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effect = effect,
            )
        }
        composable<BankAccountsDestination.Detail> {
            val viewModel = hiltViewModel<DetailBankAccountViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.effect.collectAsState(DetailBankAccountUiEffect.Idle)
            DetailBankAccountScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effect = effect,
                onBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}

private fun NavGraphBuilder.categories(navController: NavController) {
    navigation<CategoriesDestination>(startDestination = CategoriesDestination.List) {
        composable<CategoriesDestination.List> {
            val viewModel = hiltViewModel<ListCategoriesViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.effect.collectAsState(ListCategoriesUiEffect.Idle)

            LaunchedEffect(effect) {
                when (effect) {
                    ListCategoriesUiEffect.NavigateBack -> {
                        navController.popBackStack()
                    }

                    ListCategoriesUiEffect.NavigateNewCategory -> {
                        navController.navigate(CategoriesDestination.Detail(null))
                    }

                    is ListCategoriesUiEffect.NavigateUpdateCategory -> {
                        navController.navigate(CategoriesDestination.Detail((effect as ListCategoriesUiEffect.NavigateUpdateCategory).category.id))
                    }

                    else -> Unit
                }
            }

            ListCategoriesScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effect = effect,
            )
        }

        composable<CategoriesDestination.Detail> {
            val viewModel = hiltViewModel<DetailCategoryViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.effect.collectAsState(DetailCategoryUiEffect.Idle)

            LaunchedEffect(effect) {
                when (effect) {
                    DetailCategoryUiEffect.NavigateBack -> {
                        navController.popBackStack()
                    }

                    else -> Unit
                }
            }

            DetailCategoryScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effect = effect,
            )
        }
    }
}

private fun NavGraphBuilder.personalInformation(navController: NavController) {
    composable<PersonalInformationDestination> {
        PersonalInformationScreen(
            onBack = {
                navController.popBackStack()
            },
        )
    }
}


fun NavController.navigateToProfile() {
    navigate(ProfileEntryDestination)
}

private fun NavController.navigateToBankAccounts() {
    navigate(BankAccountsDestination)
}

private fun NavController.navigateToCategories() {
    navigate(CategoriesDestination)
}

private fun NavController.navigateToPersonalInformation() {
    navigate(PersonalInformationDestination)
}