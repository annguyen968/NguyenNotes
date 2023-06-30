package com.example.nguyennotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nguyennotes.presentation.screen.MainScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = HOME_GRAPH_ROUTE
    ) {
        composable(route = HOME_GRAPH_ROUTE) {
            MainScreen()
        }

    }
}
