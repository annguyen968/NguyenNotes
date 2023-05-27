package com.example.nguyennotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.nguyennotes.ui.screen.HomeScreen
import com.example.nguyennotes.ui.screen.NotificationScreen
import com.example.nguyennotes.ui.screen.ProfileScreen

@Composable
fun HomeNavGraph(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route, route = HOME_GRAPH_ROUTE) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Notification.route) {
            NotificationScreen()
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
