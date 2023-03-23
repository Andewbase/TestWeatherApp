package com.example.testweather.util.tempstrategy.strategy

import com.example.testweather.model.season.entities.TemperaturePerSeason
import kotlin.math.roundToInt

class FahrenheitTemperatureStrategy: TemperatureStrategy {
    override fun getTemperatureStrategy(temperaturePerSeason: TemperaturePerSeason): String {
        val resultTemperaturePerSeason = (
                temperaturePerSeason.temperatureMonthOne +
                        temperaturePerSeason.temperatureMonthTwo +
                        temperaturePerSeason.temperatureMonthThree) / 3
        val fahrenheitTempResult = ((9/5) * resultTemperaturePerSeason + 32).roundToInt()
        return "$fahrenheitTempResult°F"
    }
}