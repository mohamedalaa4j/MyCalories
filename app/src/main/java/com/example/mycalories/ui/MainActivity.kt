package com.example.mycalories.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.ui.theme.MyCaloriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyCaloriesTheme {
                val emptyList: MutableList<FoodItemModel> = mutableListOf()
//                HomeScreen(defaultFoodList = getFoodList())
                HomeScreen(defaultFoodList = emptyList)
            }
        }
    }
}