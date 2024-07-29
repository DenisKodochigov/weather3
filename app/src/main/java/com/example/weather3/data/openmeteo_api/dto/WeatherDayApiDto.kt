package com.example.weather3.data.openmeteo_api.dto

import com.example.weather3.domain.weatherDay
import com.example.weather3.entity.WeatherDayApi
import com.example.weather3.entity.Weathers
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDayApiDto (
    @Json(name = "latitude") override val latitude: Double = 0.0,
    @Json(name = "longitude") override val longitude: Double = 0.0,
    @Json(name = "generationtime_ms") override val generationTimeMS: Double = 0.0,
    @Json(name = "utc_offset_seconds") override val utcOffsetSeconds: Int = 0,
    @Json(name = "timezone") override val timezone: String = "",
    @Json(name = "timezone_abbreviation") override val timezoneAbbreviation: String = "",
    @Json(name = "elevation") override val elevation: Double = 0.0,
    @Json(name = "hourly_units") override val hourlyUnits: HourlyUnitsDto? = null,
    @Json(name = "hourly") override val hourly: HourlyDto? = null,
): WeatherDayApi {
    override fun toWeathers(): Weathers {
        return Weathers(weatherDay = weatherDay(hourly),)
    }
}