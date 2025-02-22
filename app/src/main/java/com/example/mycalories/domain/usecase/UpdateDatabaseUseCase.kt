package com.example.mycalories.domain.usecase

import com.example.mycalories.data.DayIntakeRecord
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.repository.Repository
import com.example.mycalories.utils.currentDate
import javax.inject.Inject


class UpdateDatabaseUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(foodItemsList: List<FoodItemModel>) {
        lateinit var record: DayIntakeRecord

        repository.getTodayRecord(currentDate()).collect { existingRecord ->
            existingRecord?.let {
                record = existingRecord.copy(foodIntake = foodItemsList)
                return@collect
            }
            record = DayIntakeRecord(currentDate(), foodItemsList, waterIntake = 0.0)
        }

        repository.insertRecord(record)
    }
}