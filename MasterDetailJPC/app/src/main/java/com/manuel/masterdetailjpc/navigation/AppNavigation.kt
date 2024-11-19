package com.manuel.masterdetailjpc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.masterdetailjpc.MainActivity
import com.manuel.masterdetailjpc.screens.DetailScreen
import com.manuel.masterdetailjpc.screens.MasterScreen
import com.manuel.masterdetailjpc.viewmodel.MainViewModel


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
    }
}