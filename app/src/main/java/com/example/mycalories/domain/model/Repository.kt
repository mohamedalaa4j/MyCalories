package com.example.mycalories.domain.model

import com.example.mycalories.data.DayIntakeDao
import com.example.mycalories.data.DayIntakeRecord
import javax.inject.Inject

interface Repository {
    suspend fun insertRecord(record: DayIntakeRecord)
    suspend fun getAllRecords(): List<DayIntakeRecord>
}