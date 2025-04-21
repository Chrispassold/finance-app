package com.chrispassold.askbuddy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chrispassold.askbuddy.ui.features.login.LoginEntryDestination
import com.chrispassold.askbuddy.ui.features.login.login
import com.chrispassold.askbuddy.ui.features.profile.profile

@Composable
fun AppRouter() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginEntryDestination) {
        login(navController)
        profile(navController)
    }

}