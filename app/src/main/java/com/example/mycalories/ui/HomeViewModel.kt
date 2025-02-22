package com.example.mycalories.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.TotalsModel
import com.example.mycalories.domain.usecase.GetTodayRecordUseCase
import com.example.mycalories.domain.usecase.UpdateDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val updateDatabaseUseCase: UpdateDatabaseUseCase,
    private val getTodayRecordUseCase: GetTodayRecordUseCase
) : ViewModel() {
    private val _foodListState = MutableStateFlow<List<FoodItemModel>>(emptyList())
    val foodListState get() = _foodListState.asStateFlow()

    private val _totalsState = MutableStateFlow(TotalsModel(0.0, 0.0, 0.0, 0.0))
    val totalsState get() = _totalsState.asStateFlow()

    init {
        getFoodListFromDb()
    }

    fun addFoodItem(foodItem: FoodItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _foodListState.value += foodItem
            calculateTotals()
            updateDatabaseUseCase(foodListState.value)
        }
    }

    fun deleteSelectedItems(selectedItems: Set<Int>) {
        viewModelScope.launch {
            selectedItems.forEach {
                _foodListState.value -= _foodListState.value[it]
            }

            calculateTotals()
            updateDatabaseUseCase(foodListState.value)
        }
    }

    private fun getFoodListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecordUseCase().collect { record ->
                record?.let {
                    _foodListState.emit(it.foodIntake)
                    calculateTotals()
                }
            }
        }
    }

    private fun calculateTotals() {
        viewModelScope.launch {
            var calories = BigDecimal(0.0)
            var protein = BigDecimal(0.0)
            var carp = BigDecimal(0.0)
            var fat = BigDecimal(0.0)
            foodListState.value.forEach { food ->
                val multiplicationCoefficient = food.weight / 100
                calories += calculate(food.calories, multiplicationCoefficient)
                protein += calculate(food.protein, multiplicationCoefficient)
                carp += calculate(food.carp, multiplicationCoefficient)
                fat += calculate(food.fat, multiplicationCoefficient)
            }
            _totalsState.value = TotalsModel(
                calories = calories.toDouble(),
                protein = protein.toDouble(),
                carp = carp.toDouble(),
                fat = fat.toDouble(),
            )

        }
    }

    private fun calculate(value: Double, multiplicationCoefficient: Double): BigDecimal {
        val num1 = BigDecimal(value)
        val num2 = BigDecimal(multiplicationCoefficient)
        val res = num1.multiply(num2).setScale(2, RoundingMode.HALF_UP)

        return res
    }

//    private fun deleteAll() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.deleteAllRecords()
//        }
//    }
}