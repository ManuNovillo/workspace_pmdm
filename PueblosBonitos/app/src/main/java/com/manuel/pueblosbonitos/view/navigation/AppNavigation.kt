package com.manuel.pueblosbonitos.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.pueblosbonitos.view.MainActivity
import com.manuel.pueblosbonitos.view.screens.MainScreen
import com.manuel.pueblosbonitos.view.screens.PuebloDetailScreen
import com.manuel.pueblosbonitos.view.screens.PueblosScreen
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun AppNavigation(mainActivity: MainActivity) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.route){
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.PueblosScreen.route) {
            PueblosScreen(navController, viewModel)
        }
        composable(AppScreens.PuebloDetailScreen.route) {
            PuebloDetailScreen(viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens(route = "MainScreen")
    object PueblosScreen: AppScreens(route = "PueblosScreen")
    object PuebloDetailScreen: AppScreens(route = "PuebloDetailScreen")
}