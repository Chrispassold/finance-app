package com.chrispassold.askbuddy.ui.features.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chrispassold.askbuddy.ui.features.login.navigateToLogin
import kotlinx.serialization.Serializable

@Serializable
private data object ProfileEntryDestination

@Serializable
private data object ProfileHomeDestination

@Serializable
private data object BankAccountsDestination

@Serializable
private data object CategoriesDestination

@Serializable
private data object PersonalInformationDestination

fun NavGraphBuilder.profile(navController: NavController) {
    navigation<ProfileEntryDestination>(startDestination = ProfileHomeDestination) {
        profileHome(navController)
        bankAccounts()
        categories()
        personalInformation()
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


private fun NavGraphBuilder.bankAccounts() {
    composable<BankAccountsDestination> {
        TODO()
    }
}

private fun NavGraphBuilder.categories() {
    composable<CategoriesDestination> {
        TODO()
    }
}

private fun NavGraphBuilder.personalInformation() {
    composable<PersonalInformationDestination> {
        TODO()
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