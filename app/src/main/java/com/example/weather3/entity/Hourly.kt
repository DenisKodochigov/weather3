package com.example.weather3.entity

interface Hourly {
    val time: List<String>
    val temperature2m: List<Double>
    val apparentTemperature: List<Double>
    val precipitationProbability: List<Int>
    val weatherCode: List<Int>
}