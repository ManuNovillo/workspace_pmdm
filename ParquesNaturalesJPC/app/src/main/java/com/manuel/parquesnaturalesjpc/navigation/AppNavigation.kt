package com.manuel.parquesnaturalesjpc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.parquesnaturalesjpc.screens.ComunidadesScreen
import com.manuel.parquesnaturalesjpc.screens.DetailScreen
import com.manuel.parquesnaturalesjpc.screens.ParquesScreen
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel


@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.ComunidadesScreen.route){
        composable(AppScreens.ComunidadesScreen.route){
           ComunidadesScreen(viewModel, navController)
        }
        composable(AppScreens.ParquesScreen.route) {
            ParquesScreen(viewModel, navController)
        }
        composable(AppScreens.DetailScreen.route) {
            DetailScreen(viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object ComunidadesScreen: AppScreens(route = "ComunidadesScreen")
    object ParquesScreen: AppScreens(route = "ParquesScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
}
