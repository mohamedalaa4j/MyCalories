package com.example.mycalories.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun currentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date())
}