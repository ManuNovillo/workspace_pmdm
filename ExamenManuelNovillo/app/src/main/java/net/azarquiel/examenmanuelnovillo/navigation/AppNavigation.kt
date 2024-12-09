package net.azarquiel.examenmanuelnovillo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.examenmanuelnovillo.screens.DetailScreen
import net.azarquiel.examenmanuelnovillo.screens.MasterScreen
import net.azarquiel.examenmanuelnovillo.view.MainActivity
import net.azarquiel.examenmanuelnovillo.viewmodel.MainViewModel

@Composable
fun AppNavigation(mainActivity: MainActivity) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MasterScreen.route){
        composable(AppScreens.MasterScreen.route){
            MasterScreen(navController, viewModel)
        }
        composable(AppScreens.DetailScreen.route) {
            DetailScreen(navController, viewModel)
        }
//        composable(AppScreens.PuebloDetailScreen.route) {
//            PuebloDetailScreen(navController, viewModel)
//        }
    }
}

sealed class AppScreens(val route: String) {
    object MasterScreen: AppScreens(route = "MasterScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
//    object PuebloDetailScreen: AppScreens(route = "PuebloDetailScreen")
}