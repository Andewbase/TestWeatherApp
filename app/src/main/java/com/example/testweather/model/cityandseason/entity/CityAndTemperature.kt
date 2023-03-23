package com.example.testweather.model.cityandseason.entity

import com.example.testweather.model.city.entities.City
import com.example.testweather.model.season.entities.TemperaturePerSeason

data class CityAndTemperature(
    val city: City,
    val temperaturePerSeason: List<TemperaturePerSeason>
)
