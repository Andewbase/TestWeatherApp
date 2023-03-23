package com.example.testweather.util.tempstrategy.strategy

import com.example.testweather.model.season.entities.TemperaturePerSeason

interface TemperatureStrategy {
    fun getTemperatureStrategy (temperaturePerSeason: TemperaturePerSeason): String
}