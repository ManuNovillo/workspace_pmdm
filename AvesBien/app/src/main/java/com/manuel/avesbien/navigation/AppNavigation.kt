package com.manuel.avesbien.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.avesbien.screens.RecursoDetailScreen
import com.manuel.avesbien.screens.RecursosScreen
import com.manuel.avesbien.screens.ZonaDetailScreen
import com.manuel.avesbien.screens.ZonasScreen
import com.manuel.avesbien.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.ZonasScreen.route
    ) {
        composable(AppScreens.ZonasScreen.route) {
            ZonasScreen(navController, viewModel)
        }

        composable(AppScreens.ZonaDetailScreen.route) {
            ZonaDetailScreen(navController, viewModel)
        }

        composable(AppScreens.RecursosScreen.route) {
            RecursosScreen(navController, viewModel)
        }

        composable(AppScreens.RecursoDetailScreen.route) {
            RecursoDetailScreen(viewModel)
        }
    }
}

sealed class AppScreens(val route: String) {
    object ZonasScreen : AppScreens(route = "ZonasScreen")
    object ZonaDetailScreen : AppScreens(route = "ZonaDetailScreen")
    object RecursosScreen : AppScreens(route = "RecursosScreen")
    object RecursoDetailScreen : AppScreens(route = "RecursoDetailScreen")
}
