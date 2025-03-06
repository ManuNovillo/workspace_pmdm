package net.azarquiel.retrofit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.retrofit.screens.GafasDetailScreen
import net.azarquiel.retrofit.screens.GafasScreen
import net.azarquiel.retrofit.screens.MarcasScreen
import net.azarquiel.retrofit.view.MainActivity
import net.azarquiel.retrofit.viewmodel.MainViewModel


@Composable
fun AppNavigation(mainActivity: MainActivity) {
    val viewModel = MainViewModel(mainActivity)
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MarcasScreen.route
    ) {
        composable(AppScreens.MarcasScreen.route) {
            MarcasScreen(navController, viewModel)
        }
        composable(AppScreens.GafasScreen.route) {
            GafasScreen(navController, viewModel)
        }
        composable(AppScreens.GafasDetailScreen.route) {
            GafasDetailScreen(navController, viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object MarcasScreen : AppScreens(route = "MarcasScreen")
    object GafasScreen : AppScreens(route = "GafasScreen")
    object GafasDetailScreen : AppScreens(route = "GafasDetailScreen")
}
