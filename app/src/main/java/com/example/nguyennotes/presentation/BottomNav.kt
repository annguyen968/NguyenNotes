package com.example.nguyennotes.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.nguyennotes.presentation.navigation.Screen

sealed class BottomNav( val route: String,
                 val title: String,
                 val icon: ImageVector
) {
    object Home : BottomNav(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Notification : BottomNav(
        route = Screen.Notification.route,
        title = "Notification",
        icon = Icons.Default.Notifications
    )

    object Profile : BottomNav(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )

    object ChatGPT : BottomNav(
        route = Screen.ChatGPT.route,
        title = "ChatGPT",
        icon = Icons.Default.Call
    )
}