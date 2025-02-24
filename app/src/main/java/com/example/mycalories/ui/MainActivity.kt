package com.example.mycalories.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycalories.ui.navigation.Routes
import com.example.mycalories.ui.navigation.getBottomNavigationItems
import com.example.mycalories.ui.screen.history.HistoryScreen
import com.example.mycalories.ui.screen.history.HistoryViewModel
import com.example.mycalories.ui.screen.home.HomeScreen
import com.example.mycalories.ui.theme.MyCaloriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCaloriesTheme {
                val navController = rememberNavController()
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            getBottomNavigationItems(this@MainActivity).forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = index == selectedItemIndex,
                                    label = { Text(text = item.title) },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                item.badgeCount?.let { count ->
                                                    Badge {
                                                        Text(text = count.toString())
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unSelectedIcon,
                                                contentDescription = item.title
                                            )
                                        }
                                    },
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(
                            bottom = innerPadding.calculateBottomPadding(),
                            top = innerPadding.calculateTopPadding()
                        ),
                        navController = navController,
                        startDestination = Routes.HomeScreen,
                    ) {
                        composable<Routes.HomeScreen> {
                            HomeScreen()
                        }

                        composable<Routes.History> {
//                            val args = it.toRoute<Routes.SecondScreenRoute>()
                            val viewModel: HistoryViewModel = hiltViewModel()
                            val historyList by viewModel.recordsState.collectAsState()
                            HistoryScreen(
                                recordsList = historyList,
                                fetchRecords = viewModel::getAllRecords
                            )
                        }
                    }
                }
            }
        }
    }
}