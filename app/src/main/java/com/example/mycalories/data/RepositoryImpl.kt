package com.example.mycalories.data

import com.example.mycalories.domain.model.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: DayIntakeDao
) : Repository {

    override suspend fun insertRecord(record: DayIntakeRecord) {
        db.insertRecord(record)
    }
}