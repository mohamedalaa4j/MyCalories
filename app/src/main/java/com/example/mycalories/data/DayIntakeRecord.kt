package com.example.mycalories.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycalories.domain.model.FoodItemModel

@Entity(tableName = "day_intake_record")
data class DayIntakeRecord(
    @PrimaryKey val date: String,
    val foodIntake: List<FoodItemModel>,
    val waterIntake: Double,
)