package com.chrispassold.askbuddy.presentation.features.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chrispassold.askbuddy.presentation.features.profile.navigateToProfile
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
    navigation<LoginEntryDestination>(startDestination = SignInDestination) {
        signInScreen(
            onNavigateToSignUp = { navController.navigateToSignUp() },
            onForgetPassword = { navController.navigateToForgetPassword() },
            onSignIn = { /*todo: go to home screen*/  navController.navigateToProfile() },
        )

        signUpScreen(
            onNavigationToSignIn = { navController.navigateToSignIn() },
            onSignUp = { navController.navigateToSignIn() },
        )
        forgotPasswordScreen()
    }
}


private fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit,
    onForgetPassword: () -> Unit,
    onSignIn: () -> Unit,
) {
    composable<SignInDestination> {
        SignInScreen(
            onNavigateToSignUp = onNavigateToSignUp,
            onForgetPassword = onForgetPassword,
            onSignIn = onSignIn,
        )
    }
}

private fun NavGraphBuilder.signUpScreen(
    onNavigationToSignIn: () -> Unit,
    onSignUp: () -> Unit,
) {
    composable<SignUpDestination> {
        SignUpScreen(
            onNavigationToSignIn = onNavigationToSignIn,
            onSignUp = onSignUp,
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