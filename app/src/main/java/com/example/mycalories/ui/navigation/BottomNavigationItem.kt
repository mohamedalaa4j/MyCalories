package com.example.mycalories.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: Routes,
    val badgeCount: Int? = null
)

fun getBottomNavigationItems() = listOf(
    BottomNavigationItem(
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        Routes.HomeScreen
    ),

    BottomNavigationItem(
        "History",
        Icons.Filled.Menu,
        Icons.Outlined.Menu,
        Routes.History
    ),

    )