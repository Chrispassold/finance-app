package com.chrispassold.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chrispassold.presentation.features.login.LoginEntryDestination
import com.chrispassold.presentation.features.login.login
import com.chrispassold.presentation.features.profile.profile

@Composable
fun AppRouter() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginEntryDestination) {
        login(navController)
        profile(navController)
    }

}