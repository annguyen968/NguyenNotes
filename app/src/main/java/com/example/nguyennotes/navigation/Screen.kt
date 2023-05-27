package com.example.nguyennotes.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
sealed class Screen(val route: String) {
    object Home: Screen(route = "Home")
    object Profile: Screen(route = "Profile")
    object Login: Screen(route = "Login")
    object Notification: Screen(route = "Notificaiton")
}