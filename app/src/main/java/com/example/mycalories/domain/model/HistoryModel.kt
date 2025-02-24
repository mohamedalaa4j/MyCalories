package com.example.mycalories.domain.model

data class HistoryModel(
    val date: RecordDate,
    val total: TotalsModel
)

@JvmInline
value class RecordDate(val date: String)