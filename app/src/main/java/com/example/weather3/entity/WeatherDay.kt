package com.example.weather3.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class WeatherDay(
    val selectDay: MutableState<Boolean> = mutableStateOf(false),
    val date: LocalDate = LocalDate.now(),
    val weatherHour: List<WeatherHour> = emptyList(),
    val middleCod: Int = 0,
    val middleTemp: Int = 0,
    val maxTemp: Int =0,
    val minTemp: Int =0,
    val middleRainfall: Int = 0,
)
