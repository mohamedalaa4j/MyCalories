package com.example.mycalories.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.usecase.GetTodayRecordUseCase
import com.example.mycalories.domain.usecase.UpdateDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val updateDatabaseUseCase: UpdateDatabaseUseCase,
    private val getTodayRecordUseCase: GetTodayRecordUseCase
) : ViewModel() {
    private val _foodListState = MutableStateFlow<List<FoodItemModel>>(emptyList())
    val foodListState get() = _foodListState.asStateFlow()

    init {
        getFoodListFromDb()
    }

    fun addFoodItem(foodItem: FoodItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _foodListState.value += foodItem
            updateDatabaseUseCase(foodListState.value)
        }
    }

    fun deleteSelectedItems(selectedItems: Set<Int>) {
        viewModelScope.launch {
            selectedItems.forEach {
                _foodListState.value -= _foodListState.value[it]
            }

            updateDatabaseUseCase(foodListState.value)
        }
    }

    private fun getFoodListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecordUseCase().collect { record ->
                record?.let {
                    _foodListState.emit(it.foodIntake)
                }
            }
        }
    }

//    private fun deleteAll() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.deleteAllRecords()
//        }
//    }
}