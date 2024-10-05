package com.example.mycalories.domain.model

data class FoodItemModel (
    val name: String,
    val calories: Double,
    val protein: Double,
    val carp: Double,
    val fat: Double
)

fun getFoodList() = listOf(
    FoodItemModel(
        "milk",
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
    ),FoodItemModel(
        "rice",
        120.5,
        4.0,
        20.0,
        2.0
    )
)