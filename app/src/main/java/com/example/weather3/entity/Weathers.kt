package com.example.weather3.entity

data class Weathers (
    val weatherDay: List<WeatherDay> = emptyList()
) {
    fun currentTemp(): String{
        return if (this.weatherDay.isNotEmpty()) {
            if (this.weatherDay[0].weatherHour.isNotEmpty()) {
                this.weatherDay[0].weatherHour[0].temperature.toInt().toString()
            } else ""
        } else ""
    }
    fun currentApparentTemp(): String{
        return if (this.weatherDay.isNotEmpty()) {
            if (this.weatherDay[0].weatherHour.isNotEmpty()) {
                this.weatherDay[0].weatherHour[0].apparentTemp.toInt().toString()
            } else ""
        } else ""
    }
    fun currentWeatherCod(): Int{
        return if (this.weatherDay.isNotEmpty()) {
            if (this.weatherDay[0].weatherHour.isNotEmpty()) {
                this.weatherDay[0].weatherHour[0].weatherCode
            } else 0
        } else 0
    }
}