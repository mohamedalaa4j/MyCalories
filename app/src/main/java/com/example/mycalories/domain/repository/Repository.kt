package com.example.mycalories.domain.repository

import com.example.mycalories.data.DayIntakeRecord
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertRecord(record: DayIntakeRecord)
    suspend fun getTodayRecord(currentData: String): Flow<DayIntakeRecord?>
    suspend fun getAllRecords(): Flow<List<DayIntakeRecord>>
    suspend fun deleteAllRecords()
}