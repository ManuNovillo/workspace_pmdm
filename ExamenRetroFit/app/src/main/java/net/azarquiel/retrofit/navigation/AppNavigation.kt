package net.azarquiel.retrofit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.masterdetailbasicjpc.MainViewModel
import net.azarquiel.masterdetailbasicjpc.screens.DetailScreen
import net.azarquiel.masterdetailbasicjpc.screens.MasterScreen


@Composable
fun AppNavigation(viewModel: MainViewModel) {
   val navController = rememberNavController()
   NavHost(navController = navController,
       startDestination = AppScreens.MasterScreen.route){
       composable(AppScreens.MasterScreen.route){
           MasterScreen(navController, viewModel)
       }
       composable(AppScreens.DetailScreen.route) {
           DetailScreen(navController, viewModel)
       }
   }
}
sealed class AppScreens(val route: String) {
   object MasterScreen: AppScreens(route = "MasterScreen")
   object DetailScreen: AppScreens(route = "DetailScreen")
}
