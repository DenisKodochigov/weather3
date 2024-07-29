package com.example.weather3.domain

import android.util.Log
import com.example.weather3.R
import com.example.weather3.data.openmeteo_api.dto.HourlyDto
import com.example.weather3.entity.Hourly
import com.example.weather3.entity.WeatherDay
import com.example.weather3.entity.WeatherHour
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import kotlin.text.Typography.tm

fun lg( text: String){
    Log.d("KDS", text)
}

fun weatherDay(hourly: Hourly?): List<WeatherDay>{
    val weathers = mutableListOf<WeatherDay>()
    val weatherDay = mutableListOf<WeatherHour>()
    val weatherCod = mutableListOf<Int>()
    var currentDate = ""
    var minTemp = 200.0
    var maxTemp = -200.0
    var middleTemp = 0.0
    var middleRainfall = 0
    hourly?.let { itHourly->
//        lg("itHourly $itHourly")
        currentDate = itHourly.time[0].substringBefore("T")
        itHourly.time.forEachIndexed {index, tm ->
            if (currentDate != tm.substringBefore("T")){
                weathers.add(
                    WeatherDay(
                        date = LocalDate.parse(currentDate),
                        weatherHour = weatherDay.toList(),
                        middleCod = weatherCod.groupBy { it }.maxBy { it.value.size }.key,
                        middleTemp = (middleRainfall / weatherDay.count()),
                        middleRainfall = (middleRainfall / weatherDay.count()),
                        maxTemp = maxTemp.toInt(),
                        minTemp = minTemp.toInt(),
                    )
                )
                weatherDay.clear()
                weatherCod.clear()
                minTemp = 200.0
                maxTemp = -200.0
                middleTemp = 0.0
                middleRainfall = 0
                currentDate = tm.substringBefore("T")
            }
            weatherDay.add(
                WeatherHour(
                    time = LocalTime.parse(tm.substringAfter("T")),
                    temperature = itHourly.temperature2m[index],
                    apparentTemp = itHourly.apparentTemperature[index],
                    precipitation = itHourly.precipitationProbability[index],
                    weatherCode = itHourly.weatherCode[index]
                )
            )
            weatherCod.add(itHourly.weatherCode[index])
            middleTemp += itHourly.temperature2m[index]
            middleRainfall += itHourly.precipitationProbability[index]
            if (itHourly.temperature2m[index] > maxTemp) maxTemp = itHourly.temperature2m[index]
            if (itHourly.temperature2m[index] < minTemp) minTemp = itHourly.temperature2m[index]
        }
    }
    lg("weatherDay.count(): ${weatherDay.count()}")
    weathers.add(
        WeatherDay(
            date = LocalDate.parse(currentDate),
            weatherHour = weatherDay.toList(),
            middleCod = weatherCod.groupBy { it }.maxBy { it.value.size }.key,
            middleTemp = (middleRainfall / weatherDay.count()),
            middleRainfall = (middleRainfall / weatherDay.count()),
            maxTemp = maxTemp.toInt(),
            minTemp = minTemp.toInt(),
        )
    )
//    lg("weathers ${weathers.last()}")
    return weathers
}

val DayOfWeek.rus: Int get() = rusDayOfWeek(this.value)
val Month.rus: Int get() = rusMonth(this.value)

fun rusDayOfWeek(dayOfWeek: Int): Int {
    return when(dayOfWeek){
        1 -> R.string.monday
        2 -> R.string.tuesday
        3 -> R.string.wednesday
        4 -> R.string.thursday
        5 -> R.string.friday
        6 -> R.string.saturday
        7 -> R.string.sunday
        else -> R.string.sunday
    }
}
fun rusMonth(dayOfWeek: Int): Int {
    return when(dayOfWeek){
        1 -> R.string.january
        2 -> R.string.february
        3 -> R.string.march
        4 -> R.string.april
        5 -> R.string.may
        6 -> R.string.june
        7 -> R.string.july
        8 -> R.string.august
        9 -> R.string.september
        10 -> R.string.october
        11 -> R.string.november
        12 -> R.string.december
        else -> R.string.december
    }
}