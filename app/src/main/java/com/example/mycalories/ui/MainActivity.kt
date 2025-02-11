package com.example.mycalories.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.ui.theme.MyCaloriesTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        getDate()
    }

    private fun getDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate: String = sdf.format(Date())
        Log.i("myTag", currentDate)
    }
}