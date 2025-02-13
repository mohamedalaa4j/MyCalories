package com.example.mycalories.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayIntakeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: DayIntakeRecord)

    @Query("SELECT * FROM day_intake_record WHERE date = :date")
    suspend fun getRecordByDate(date: String): DayIntakeRecord?

    @Query("SELECT * FROM day_intake_record")
    suspend fun getAllRecords(): List<DayIntakeRecord>
}