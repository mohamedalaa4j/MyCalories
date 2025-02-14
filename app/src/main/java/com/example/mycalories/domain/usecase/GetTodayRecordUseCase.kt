package com.example.mycalories.domain.usecase

import com.example.mycalories.data.DayIntakeRecord
import com.example.mycalories.domain.repository.Repository
import com.example.mycalories.utils.currentDate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayRecordUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<DayIntakeRecord?> {
        return repository.getTodayRecord(currentDate())
    }
}