package net.azarquiel.chistesbien.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.chistesbien.screens.CategoriasScreen
import net.azarquiel.chistesbien.screens.ChisteDetailScreen
import net.azarquiel.chistesbien.screens.ChistesScreen
import net.azarquiel.chistesbien.view.MainActivity
import net.azarquiel.chistesbien.viewmodel.MainViewModel

@Composable
fun AppNavigation(mainActivity: MainActivity) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(
        navController = navController,
        startDestination = AppScreens.CategoriasScreen.route
    ) {
        composable(AppScreens.CategoriasScreen.route) {
            CategoriasScreen(navController, viewModel)
        }

        composable(AppScreens.ChistesScreen.route) {
            ChistesScreen(navController, viewModel)
        }

        composable(AppScreens.ChisteDetailScreen.route) {
            ChisteDetailScreen(navController, viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object CategoriasScreen : AppScreens(route = "CategoriasScreen")
    object ChistesScreen : AppScreens(route = "ChistesScreen")
    object ChisteDetailScreen : AppScreens(route = "ChisteDetailScreen")
}