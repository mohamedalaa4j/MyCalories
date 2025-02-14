package com.example.mycalories.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.repository.Repository
import com.example.mycalories.domain.usecase.GetTodayRecordUseCase
import com.example.mycalories.domain.usecase.AddFoodItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: Repository,
    private val addFoodItemUseCase: AddFoodItemUseCase,
    private val getTodayRecordUseCase: GetTodayRecordUseCase
) : ViewModel() {
    private val _foodListState = MutableStateFlow<List<FoodItemModel>>(emptyList())
    val foodListState get() = _foodListState.asStateFlow()

    init {
//        deleteAll()
        getFoodList()
    }

     fun addFoodItem(foodItem: FoodItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addFoodItemUseCase(foodItem)
            getFoodList()
        }
    }

    private fun getFoodList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecordUseCase().collect { record ->
                record?.let {
                    _foodListState.emit(it.foodIntake)
                }
                Log.i("myTag", "foodListState" + foodListState.value.toString())
            }
        }
    }

    private fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.deleteAllRecords()
            }
        }
    }
}