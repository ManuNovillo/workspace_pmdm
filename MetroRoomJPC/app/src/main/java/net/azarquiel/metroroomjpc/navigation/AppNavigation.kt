package net.azarquiel.metroroomjpc.navigation

import EstacionesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.metroroomjpc.MainActivity
import net.azarquiel.metroroomjpc.MainViewModel
import net.azarquiel.metroroomjpc.screens.MainScreen


@Composable
fun AppNavigation(mainActivity: MainActivity) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.route){
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.EstacionesScreen.route) {
            EstacionesScreen(navController, viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens(route = "MainScreen")
    object EstacionesScreen: AppScreens(route = "EstacionesScreen")
}