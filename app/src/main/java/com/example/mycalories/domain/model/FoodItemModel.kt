package com.example.mycalories.domain.model

import android.content.Context
import com.example.mycalories.R

data class FoodItemModel(
    val name: String,
    val calories: Double,
    val protein: Double,
    val carp: Double,
    val fat: Double,
    var weight: Double = 100.0
)

fun getFoodList(context: Context) = mutableListOf(
    FoodItemModel(
        context.getString(R.string.chicken_breast),
        165.0,
        31.0,
        200.0,
        3.6
    ),

    FoodItemModel(
        context.getString(R.string.chicken_breast_fried),
        260.0,
        25.0,
        9.28,
        12.85
    ),

    FoodItemModel(
        context.getString(R.string.rice),
        130.0,
        2.6,
        28.0,
        0.3
    ),

    FoodItemModel(
        context.getString(R.string.bread),
        300.0,
        9.4,
        56.0,
        2.3
    ),

    FoodItemModel(
        context.getString(R.string.egg),
        71.0,
        6.5,
        0.36,
        4.7
    ),

    FoodItemModel(
        context.getString(R.string.lentils),
        116.0,
        9.0,
        20.0,
        0.4
    ),

    FoodItemModel(
        context.getString(R.string.taro),
        122.0,
        1.5,
        26.5,
        0.2
    ),

    FoodItemModel(
        context.getString(R.string.falafel),
        333.0,
        13.0,
        32.0,
        18.0
    ),

    FoodItemModel(
        context.getString(R.string.beans),
        347.0,
        21.0,
        63.0,
        1.2
    ),

    FoodItemModel(
        context.getString(R.string.tuna),
        238.0,
        33.6,
        0.0,
        9.8
    ),

    FoodItemModel(
        context.getString(R.string.pea),
        81.0,
        5.0,
        14.0,
        0.4
    ),

    FoodItemModel(
        context.getString(R.string.potato),
        77.0,
        2.0,
        17.0,
        0.1
    ),

    FoodItemModel(
        context.getString(R.string.meat),
        143.0,
        26.0,
        0.0,
        3.5
    ),

    FoodItemModel(
        context.getString(R.string.french_fries),
        312.0,
        3.4,
        41.0,
        15.0
    ),

    FoodItemModel(
        context.getString(R.string.pasta),
        131.0,
        5.0,
        25.0,
        1.1
    ),

    FoodItemModel(
        context.getString(R.string.chicken_wings_fried),
        285.0,
        25.38,
        4.44,
        17.71
    ),

    FoodItemModel(
        context.getString(R.string.hot_dog),
        290.0,
        10.0,
        4.2,
        26.0
    ),

    FoodItemModel(
        context.getString(R.string.tilapia),
        129.0,
        26.0,
        0.0,
        2.7
    ),

    FoodItemModel(
        context.getString(R.string.green_beans),
        31.0,
        1.8,
        7.0,
        0.1
    ),

    FoodItemModel(
        context.getString(R.string.biscuits_entity),
        238.4,
        3.36,
        35.36,
        9.12
    ),

    FoodItemModel(
        context.getString(R.string.oats),
        374.0,
        11.0,
        60.0,
        8.0
    ),

    FoodItemModel(
        context.getString(R.string.liver),
        165.0,
        26.0,
        3.8,
        4.4
    ),

    FoodItemModel(
        "لانشون",
        230.0,
        13.0,
        8.0,
        16.0
    ),

    FoodItemModel(
        "شيبسى تايجر",
        124.55,
        1.6,
        12.9,
        7.75
    ),

    FoodItemModel(
        "بسكويت دورو 15جم",
        73.35,
        0.9,
        10.5,
        2.9
    ),

    )