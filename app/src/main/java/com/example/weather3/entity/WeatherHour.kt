package com.example.weather3.entity

import java.time.LocalTime

data class WeatherHour(
    val time: LocalTime = LocalTime.now(),
    val temperature: Double = 0.0,
    val apparentTemp: Double = 0.0,
    val precipitation: Int = 0,
    val weatherCode: Int = 0,
)
