package com.chrispassold.presentation.features.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chrispassold.presentation.features.profile.navigateToProfile
import kotlinx.serialization.Serializable

@Serializable
data object LoginEntryDestination

@Serializable
private data object SignInDestination

@Serializable
private data object SignUpDestination

@Serializable
private data object ForgetPasswordDestination

fun NavGraphBuilder.login(navController: NavController) {
    navigation<LoginEntryDestination>(
        startDestination = SignInDestination,
    ) {
        signInScreen(
            navigateToSignUp = { navController.navigateToSignUp() },
            navigateToForgetPassword = { navController.navigateToForgetPassword() },
            navigateToHome = { /*todo: go to home screen*/  navController.navigateToProfile() },
        )

        signUpScreen(
            onNavigationToSignIn = { navController.navigateToSignIn() },
            onSignUp = { navController.navigateToSignIn() },
        )
        forgotPasswordScreen()
    }
}


private fun NavGraphBuilder.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<SignInDestination> {
        SignInScreen(
            viewModel = hiltViewModel(),
            navigateToSignUp = navigateToSignUp,
            navigateToForgetPassword = navigateToForgetPassword,
            navigateToHome = navigateToHome,
        )
    }
}

private fun NavGraphBuilder.signUpScreen(
    onNavigationToSignIn: () -> Unit,
    onSignUp: () -> Unit,
) {
    composable<SignUpDestination> {
        SignUpScreen(
            viewModel = hiltViewModel(),
            onAlreadyHaveAccount = onNavigationToSignIn,
            navigateOnSignUp = onSignUp,
        )
    }
}

private fun NavGraphBuilder.forgotPasswordScreen() {
    composable<ForgetPasswordDestination> {
        TODO()
    }
}


fun NavController.navigateToLogin() {
    navigate(LoginEntryDestination) {
        popUpTo(0) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToSignIn() {
    navigate(SignInDestination) {
        popUpTo(0) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToSignUp() {
    navigate(SignUpDestination)
}

private fun NavController.navigateToForgetPassword() {
    navigate(ForgetPasswordDestination)
}