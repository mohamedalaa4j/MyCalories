package com.example.mycalories.domain.usecase

import com.example.mycalories.data.DayIntakeRecord
import com.example.mycalories.domain.repository.Repository
import javax.inject.Inject

class GetAllRecordsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<DayIntakeRecord> {
        return repository.getAllRecords()
    }
}