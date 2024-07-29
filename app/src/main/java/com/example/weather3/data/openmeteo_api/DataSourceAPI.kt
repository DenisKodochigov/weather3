package com.example.weather3.data.openmeteo_api

import com.example.weather3.entity.LocApp
import com.example.weather3.entity.WeatherDayApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class DataSourceAPI @Inject constructor(private val openMeteo: OpenMeteoAPI) {
    suspend fun getWeather(locApp: LocApp): WeatherDayApi {
        val weatherDay = openMeteo.getWeatherHourly(
            forecastDays = locApp.forecastDays,
            latitude = locApp.latitude,
            longitude = locApp.longitude,)
//        lg("DataSourceAPI $weatherDay")
        delay(3000L)
        return weatherDay
    }
}