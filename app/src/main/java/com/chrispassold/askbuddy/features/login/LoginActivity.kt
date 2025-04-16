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
import com.chrispassold.askbuddy.features.login.LoginComponent
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
    NavHost(navController = navController, startDestination = Routes.Login.Start) {
        composable<Routes.Login.Start> {
            LoginComponent(
                onCreateAccount = { navController.navigate(Routes.Login.CreateAccount) },
                onForgetPassword = { navController.navigate(Routes.Login.ForgetPassword) },
                onSocialMediaLogin = { /*todo*/ },
                onSubmit = { /*todo*/ },
            )
        }
        composable<Routes.Login.CreateAccount> { /*todo*/ }
        composable<Routes.Login.ForgetPassword> { /*todo*/ }
        // Add more destinations similarly.
    }

}