package com.manuel.acbroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.acbroom.screens.JugadorScreen
import com.manuel.acbroom.screens.MainScreen
import com.manuel.acbroom.view.MainActivity
import com.manuel.acbroom.viewmodel.MainViewModel


@Composable
fun AppNavigation(mainActivity: MainActivity
) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.route){
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.JugadorScreen.route) {
            JugadorScreen(viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens(route = "MainScreen")
    object JugadorScreen: AppScreens(route = "JugadorScreen")
}
