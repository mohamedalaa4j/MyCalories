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
        0.0,
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
        context.getString(R.string.ground_meat),
        241.0,
        24.0,
        0.0,
        15.0
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
        context.getString(R.string.milk),
        63.0,
        3.4,
        4.6,
        3.6
    ),

    FoodItemModel(
        context.getString(R.string.sugar),
        387.0,
        0.0,
        100.0,
        0.0
    ),

    FoodItemModel(
        "My Nescafe Blend",
        117.29,
        3.1,
        13.48,
        5.86
    ),

    FoodItemModel(
        "My Cappuccino Blend",
        96.18,
        3.3,
        12.95,
        3.7
    ),

    FoodItemModel(
        "Break Nescafe 2X1 (12g)",
        63.4,
        0.52,
        6.8,
        3.8
    ),

    FoodItemModel(
        "Nestle Nescafe GOLD (21g)",
        95.0,
        0.6,
        15.8,
        3.3
    ),

    FoodItemModel(
        "Break Cappuccino (18.5g)",
        78.5,
        1.5,
        13.96,
        1.87
    ),

    FoodItemModel(
        "لوبيا بيضاء",
        67.0,
        6.0,
        13.0,
        0.7
    ),

    FoodItemModel(
        "لوبيا عين سوداء",
        116.0,
        8.0,
        21.0,
        0.5
    ),

    FoodItemModel(
        "كشرى مصرى",
        318.0,
        11.0,
        60.0,
        3.6
    ),

    FoodItemModel(
        "كشرى عدس اصفر",
        150.0,
        5.8,
        24.0,
        0.35
    ),

    FoodItemModel(
        "محشى كرنب",
        188.38,
        10.05,
        4.5,
        14.38
    ),

    FoodItemModel(
        "محشى كوسه",
        86.0,
        4.9,
        14.7,
        1.5
    ),

    FoodItemModel(
        "زيت حار/زيتون/عادى",
        884.0,
        0.0,
        0.0,
        100.0
    ),

    FoodItemModel(
        "طحينة",
        595.0,
        17.0,
        21.0,
        54.0
    ),

    FoodItemModel(
        "لانشون",
        230.0,
        13.0,
        8.0,
        16.0
    ),

    FoodItemModel(
        "جبنه رومى",
        407.0,
        25.0,
        1.0,
        34.0
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

    FoodItemModel(
        "بسكويت شاتوه (13 جم)",
        528.0,
        8.0,
        62.0,
        24.0
    ),

    FoodItemModel(
        "بسكويت بمبو",
        141.18,
        1.6,
        18.9,
        7.2
    ),

    FoodItemModel(
        "مولتو (قطعه)",
        247.95,
        4.39,
        29.07,
        12.54
    ),

    FoodItemModel(
        "اندومى شعيرة مقلية",
        380.0,
        7.0,
        49.0,
        17.0
    ),

    )