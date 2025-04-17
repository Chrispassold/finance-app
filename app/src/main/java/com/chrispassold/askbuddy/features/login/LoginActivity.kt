package com.chrispassold.askbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrispassold.askbuddy.features.login.SignUpScreen
import com.chrispassold.askbuddy.features.login.SignInScreen
import com.chrispassold.askbuddy.ui.routes.Routes
import com.chrispassold.askbuddy.ui.theme.AppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        LoginRouter(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginRouter(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Routes.Login.SignIn) {
        composable<Routes.Login.SignIn> {
            SignInScreen(
                onNavigateToSignUp = { navController.navigate(Routes.Login.SignUp) },
                onForgetPassword = { navController.navigate(Routes.Login.ForgetPassword) },
                onSignIn = { /*todo*/ },
            )
        }
        composable<Routes.Login.SignUp> {
            SignUpScreen(
                onNavigationToSignIn = {
                    if (!navController.popBackStack()) {
                        navController.navigate(Routes.Login.SignIn) {
                            popUpTo(Routes.Login.SignUp) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                onSignUp = { /*todo*/ },
            )

        }
        composable<Routes.Login.ForgetPassword> { /*todo*/ }
        // Add more destinations similarly.
    }

}