package com.manuel.pueblosbonitos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.pueblosbonitos.view.MainActivity
import com.manuel.pueblosbonitos.screens.MainScreen
import com.manuel.pueblosbonitos.screens.PuebloDetailScreen
import com.manuel.pueblosbonitos.screens.PueblosScreen
import com.manuel.pueblosbonitos.screens.WebPuebloScreen
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
            PuebloDetailScreen(navController, viewModel)
        }
        composable(AppScreens.WebPuebloScreen.route) {
            WebPuebloScreen(viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens(route = "MainScreen")
    object PueblosScreen: AppScreens(route = "PueblosScreen")
    object PuebloDetailScreen: AppScreens(route = "PuebloDetailScreen")
    object WebPuebloScreen: AppScreens(route = "WebPuebloScreen")
}