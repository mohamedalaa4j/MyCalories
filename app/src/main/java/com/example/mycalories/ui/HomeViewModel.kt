package com.example.mycalories.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.data.DayIntakeRecord
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    init {
        Log.e("myTag", "init VM")
        insert()
    }

    private fun insert() {
        viewModelScope.launch {
            delay(1000)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate: String = sdf.format(Date())
            val foodModel = FoodItemModel(
                "food name", 20.0, 20.0, 20.0, 20.0
            )

            repository.insertRecord(
                DayIntakeRecord(
                    currentDate,
                    listOf(foodModel),
                    1.0
                )
            )
            Log.e("myTag", "DB insert done!")
        }
    }
}