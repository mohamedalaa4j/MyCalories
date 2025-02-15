package com.example.mycalories.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.getFoodList
import com.example.mycalories.domain.repository.Repository
import com.example.mycalories.domain.usecase.AddFoodItemUseCase
import com.example.mycalories.domain.usecase.GetTodayRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _lazyList = MutableStateFlow<List<Int>>(emptyList())
     val lazyList get() = _lazyList.asStateFlow()

    fun addLazy(index: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

//            _lazyList.value += index
                _lazyList.value = (_lazyList.value + index) .toList()
                Log.i("myTag", "listAfterAdd:${lazyList.value}")

                val isSelected = lazyList.value.contains(index)

                removeTest(index)
            }
        }
    }

    fun removeLazy(index: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list = _lazyList.value.toMutableList()
                list.removeAt(index)
                _lazyList.value = list.toList()
                _lazyList.value = emptyList()

                Log.i("myTag", "listAfterRemove:${lazyList.value}")

                val isSelected = lazyList.value.contains(index)
            }
        }
    }


    init {
//        deleteAll()

//        getFoodList()
        viewModelScope.launch {
            addFoodItem(
                getFoodList()[0]
            )

            addFoodItem(
                getFoodList()[1]
            )
            addFoodItem(
                getFoodList()[2]
            )

            getFoodListFromDb()
        }


    }

    fun addFoodItem(foodItem: FoodItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addFoodItemUseCase(foodItem)
//            getFoodListFromDb()
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

    fun addTest(foodItem: FoodItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _foodListState.value += foodItem
        }
    }

    fun removeTest(itemIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentList = _foodListState.value.toMutableList()
            currentList.removeAt(itemIndex)
            _foodListState.emit(currentList)
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