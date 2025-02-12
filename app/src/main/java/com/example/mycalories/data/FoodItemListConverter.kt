package com.example.mycalories.data

import androidx.room.TypeConverter
import com.example.mycalories.domain.model.FoodItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodItemListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFoodItemList(foodItems: List<FoodItemModel>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun toFoodItemList(data: String): List<FoodItemModel> {
        val listType = object : TypeToken<List<FoodItemModel>>() {}.type
        return gson.fromJson(data, listType)
    }
}