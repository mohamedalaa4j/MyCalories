package com.example.mycalories.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DayIntakeRecord::class], version = 1, exportSchema = false)
@TypeConverters(FoodItemListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): DayIntakeDao
}