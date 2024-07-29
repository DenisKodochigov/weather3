package com.example.weather3.entity

data class LocApp(
    val forecastDays: Int = 14,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val city: String = "",
)
