package com.example.mycalories.domain.model

data class FoodItemModel (
    val name: String,
    val calories: Double,
    val protein: Double,
    val carp: Double,
    val fat: Double
)

fun getFoodList() = mutableListOf(
    FoodItemModel(
        "milk",
        220.0,
        4.0,
        20.0,
        2.0
    ),FoodItemModel(
        "rice",
        120.5,
        4.0,
        20.0,
        2.0
    ),FoodItemModel(
        "rice",
        120.5,
        4.0,
        20.0,
        2.0
    ),FoodItemModel(
        "rice",
        120.5,
        4.0,
        20.0,
        2.0
    ), FoodItemModel(
        "milk",
        220.0,
        4.0,
        20.0,
        2.0
    )
)