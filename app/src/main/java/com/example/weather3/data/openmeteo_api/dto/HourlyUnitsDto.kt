package com.example.weather3.data.openmeteo_api.dto

import com.example.weather3.entity.HourlyUnits
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnitsDto(
    @Json(name = "time") override val time: String,
    @Json(name = "temperature_2m") override val temperature2m: String,
    @Json(name = "apparent_temperature") override val apparentTemperature: String,
    @Json(name = "precipitation_probability") override val precipitationProbability: String,
    @Json(name = "weather_code") override val weatherCode: String
): HourlyUnits
