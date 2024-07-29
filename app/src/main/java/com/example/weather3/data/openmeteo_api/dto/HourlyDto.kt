package com.example.weather3.data.openmeteo_api.dto

import com.example.weather3.entity.Hourly
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyDto(
    @Json(name = "time") override val time: List<String> = emptyList(),
    @Json(name = "temperature_2m") override val temperature2m: List<Double> = emptyList(),
    @Json(name = "apparent_temperature") override val apparentTemperature: List<Double> = emptyList(),
    @Json(name = "precipitation_probability") override val precipitationProbability: List<Int> = emptyList(),
    @Json(name = "weather_code") override val weatherCode: List<Int> = emptyList(),
): Hourly
