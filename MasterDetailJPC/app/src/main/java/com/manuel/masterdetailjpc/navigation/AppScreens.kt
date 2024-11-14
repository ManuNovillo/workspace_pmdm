package com.manuel.masterdetailjpc.navigation

sealed class AppScreens(val route: String) {
   object MasterScreen: AppScreens(route = "MasterScreen")
   object DetailScreen: AppScreens(route = "DetailScreen")
}
