package com.example.testweather.model.season.entities

data class Season (
    val name: String,
    val nameMonthOne: String,
    val nameMonthTwo: String,
    val nameMonthThree: String,
    val temperatureMonthOne: Double,
    val temperatureMonthTwo: Double,
    val temperatureMonthThree: Double
)