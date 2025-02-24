package com.example.mycalories.ui.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.mycalories.R

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: Routes,
    val badgeCount: Int? = null
)

fun getBottomNavigationItems(context: Context) = listOf(
    BottomNavigationItem(
        context.getString(R.string.home),
        Icons.Filled.Home,
        Icons.Outlined.Home,
        Routes.HomeScreen
    ),

    BottomNavigationItem(
        context.getString(R.string.history),
        Icons.Filled.Menu,
        Icons.Outlined.Menu,
        Routes.History
    ),

    )