package com.example.weather3.data.openmeteo_api


import com.example.weather3.data.openmeteo_api.dto.WeatherDayApiDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OpenMeteoAPI {
    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("/v1/forecast?hourly=temperature_2m,apparent_temperature,precipitation_probability,weather_code")
    suspend fun getWeatherHourly(
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude: Double,
        @Query("forecast_days") forecastDays: Int,): WeatherDayApiDto

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("/v1/forecast?daily=weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,precipitation_probability_max")
    suspend fun getWeatherDaily(
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude: Double,
        @Query("forecast_days") forecastDays: Int,): WeatherDayApiDto
}
