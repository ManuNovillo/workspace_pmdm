package com.manuel.masterdetailjpc.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuel.masterdetailjpc.screens.DetailScreen
import com.manuel.masterdetailjpc.screens.MasterScreen
import com.manuel.masterdetailjpc.ui.theme.MasterDetailJPCTheme
import com.manuel.masterdetailjpc.viewmodel.MainViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MasterDetailJPCTheme {
                AppNavigation()
            }
        }
    }
}
