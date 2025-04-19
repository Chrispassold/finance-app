package com.chrispassold.askbuddy.features.profile

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
import com.chrispassold.askbuddy.ui.routes.Routes
import com.chrispassold.askbuddy.ui.theme.AppTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Router(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Router(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Routes.Profile.Profile) {
        composable<Routes.Profile.Profile> {

        }

        composable<Routes.Profile.BankAccounts> {
            TODO("BANK ACCOUNTS IN THE FUTURE")
        }

        composable<Routes.Profile.Categories> {
            TODO("CATEGORIES IN THE FUTURE")
        }

        composable<Routes.Profile.PersonalInformation> {
            TODO("PERSONAL INFORMATION IN THE FUTURE")
        }

        composable<Routes.Profile.CreditCards> {
            TODO("CREDIT CARDS IN THE FUTURE")
        }

        composable<Routes.Profile.ExitApp> {
            TODO("EXIT APP IN THE FUTURE")
        }

    }

}