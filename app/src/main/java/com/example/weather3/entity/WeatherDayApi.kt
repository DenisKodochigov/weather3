package com.example.weather3.entity

interface WeatherDayApi {
    val latitude: Double
    val longitude: Double
    val generationTimeMS: Double
    val utcOffsetSeconds: Int
    val timezone: String
    val timezoneAbbreviation: String
    val elevation: Double
    val hourlyUnits: HourlyUnits?
    val hourly: Hourly?

    fun toWeathers(): Weathers
}