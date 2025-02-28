package com.example.mycalories.ui.screen.history

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.R
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.HistoryModel
import com.example.mycalories.domain.model.RecordDate
import com.example.mycalories.domain.model.TotalsModel
import com.example.mycalories.domain.usecase.GetAllRecordsUseCase
import com.example.mycalories.utils.currentDate
import com.example.mycalories.utils.yesterdayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllRecordsUseCase: GetAllRecordsUseCase
) : ViewModel() {
    private val _recordsState = MutableStateFlow<List<HistoryModel>?>(null)
    val recordsState get() = _recordsState.asStateFlow()

    init {
//        getAllRecords()
    }

     fun getAllRecords(context: Context) {
         Log.i("myTag","getAllRecords")
        viewModelScope.launch(Dispatchers.IO) {
            val recordsState: MutableList<HistoryModel> = mutableListOf()
            val result = getAllRecordsUseCase()

            result.sortedBy { it.date }.reversed().forEach { day ->
                val date = when (day.date) {
                    currentDate() -> context.getString(R.string.today)
                    yesterdayDate() -> context.getString(R.string.yesterday)
                    else -> day.date
                }
                val totals = calculateTotals(day.foodIntake)
                recordsState.add(
                    HistoryModel(
                        date = RecordDate(date),
                        total = totals
                    )
                )
            }

            _recordsState.value = recordsState
        }
    }

    private fun calculateTotals(foodList: List<FoodItemModel>): TotalsModel {
        var calories = BigDecimal(0.0)
        var protein = BigDecimal(0.0)
        var carp = BigDecimal(0.0)
        var fat = BigDecimal(0.0)

        foodList.forEach { food ->
            val multiplicationCoefficient = food.weight / 100
            calories += calculate(food.calories, multiplicationCoefficient)
            protein += calculate(food.protein, multiplicationCoefficient)
            carp += calculate(food.carp, multiplicationCoefficient)
            fat += calculate(food.fat, multiplicationCoefficient)
        }

        return TotalsModel(
            calories = calories.toDouble(),
            protein = protein.toDouble(),
            carp = carp.toDouble(),
            fat = fat.toDouble(),
        )
    }


    private fun calculate(value: Double, multiplicationCoefficient: Double): BigDecimal {
        val num1 = BigDecimal(value)
        val num2 = BigDecimal(multiplicationCoefficient)
        val res = num1.multiply(num2).setScale(2, RoundingMode.HALF_UP)

        return res
    }
}