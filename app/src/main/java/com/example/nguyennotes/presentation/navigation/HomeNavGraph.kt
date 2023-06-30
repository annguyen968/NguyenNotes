package com.example.nguyennotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nguyennotes.presentation.chatgpt.ChatGPTScreen
import com.example.nguyennotes.presentation.home.HomeScreen
import com.example.nguyennotes.presentation.notification.NotificationScreen
import com.example.nguyennotes.presentation.profile.ProfileScreen

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
        composable(route = Screen.ChatGPT.route) {
            ChatGPTScreen()
        }
    }
}
