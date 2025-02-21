package com.example.mycalories.domain.model

data class FoodItemModel(
    val name: String,
    val calories: Double,
    val protein: Double,
    val carp: Double,
    val fat: Double,
    val weight: Double = 100.0
)

fun getFoodList() = mutableListOf(
    FoodItemModel(
        "Chicken Breast",
        165.0,
        31.0,
        200.0,
        3.6
    ),

    FoodItemModel(
        "Chicken Breast Fried",
        260.0,
        25.0,
        9.28,
        12.85
    ),

    FoodItemModel(
        "Rice",
        130.0,
        2.6,
        28.0,
        0.3
    ),

    FoodItemModel(
        "Bread",
        300.0,
        9.4,
        56.0,
        2.3
    ),

    FoodItemModel(
        "Egg",
        71.0,
        6.5,
        0.36,
        4.7
    ),

    FoodItemModel(
        "Lentils",
        116.0,
        9.0,
        20.0,
        0.4
    ),

    FoodItemModel(
        "Taro",
        122.0,
        1.5,
        26.5,
        0.2
    ),

    FoodItemModel(
        "Falafel",
        333.0,
        13.0,
        32.0,
        18.0
    ),

    FoodItemModel(
        "Beans",
        347.0,
        21.0,
        63.0,
        1.2
    ),

    FoodItemModel(
        "Tuna",
        238.0,
        33.6,
        0.0,
        9.8
    ),

    FoodItemModel(
        "Pea",
        81.0,
        5.0,
        14.0,
        0.4
    ),

    FoodItemModel(
        "Potato",
        77.0,
        2.0,
        17.0,
        0.1
    ),

    FoodItemModel(
        "Meat",
        143.0,
        26.0,
        0.0,
        3.5
    ),

    FoodItemModel(
        "French fries",
        312.0,
        3.4,
        41.0,
        15.0
    ),

    FoodItemModel(
        "Pasta",
        131.0,
        5.0,
        25.0,
        1.1
    ),

    FoodItemModel(
        "Chicken wings fried",
        285.0,
        25.38,
        4.44,
        17.71
    ),

    FoodItemModel(
        "Hot Dog",
        290.0,
        10.0,
        4.2,
        26.0
    ),

    FoodItemModel(
        "Tilapia",
        129.0,
        26.0,
        0.0,
        2.7
    ),

    FoodItemModel(
        "Green beans",
        31.0,
        1.8,
        7.0,
        0.1
    ),

    FoodItemModel(
        "Biscuits entity",
        238.4,
        3.36,
        35.36,
        9.12
    ),

    FoodItemModel(
        "Oats",
        374.0,
        11.0,
        60.0,
        8.0
    ),

    FoodItemModel(
        "Liver",
        165.0,
        26.0,
        3.8,
        4.4
    ),

    FoodItemModel(
        "Liver",
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