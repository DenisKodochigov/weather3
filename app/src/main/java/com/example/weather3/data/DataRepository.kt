package com.example.weather3.data

import com.example.weather3.data.openmeteo_api.DataSourceAPI
import com.example.weather3.entity.LocApp
import com.example.weather3.entity.WeatherDayApi
import com.example.weather3.entity.Weathers
import com.example.weather3.service_location.LocationManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository  @Inject constructor(
    private val dataOpenMeteo: DataSourceAPI,
    private val locationManager: LocationManager,){

    suspend fun getLocation() = locationManager.getLocation()

    suspend fun getWeathers(locApp: LocApp): Weathers {
        locationManager.cancel()
        return dataOpenMeteo.getWeather(locApp).toWeathers()
    }
    suspend fun getLocationFromAddress(city: String) = locationManager.getLocation(city)
}