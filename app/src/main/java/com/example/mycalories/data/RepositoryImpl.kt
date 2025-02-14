package com.example.mycalories.data

import com.example.mycalories.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: DayIntakeDao
) : Repository {

    override suspend fun insertRecord(record: DayIntakeRecord) {
        db.insertRecord(record)
    }

    override suspend fun getTodayRecord(currentData: String): Flow<DayIntakeRecord?> {
        return flowOf(db.getRecordByDate(currentData))
    }

    override suspend fun getAllRecords(): Flow<List<DayIntakeRecord>> {
        return flowOf(db.getAllRecords())
    }

    override suspend fun deleteAllRecords() {
        db.deleteAll()
    }
}