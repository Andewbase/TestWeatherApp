package com.example.testweather.util.tempstrategy.strategy

import android.annotation.SuppressLint
import com.example.testweather.model.season.entities.TemperaturePerSeason
import kotlin.math.roundToInt

class CelsiusTemperatureStrategy: TemperatureStrategy {

    @SuppressLint("SuspiciousIndentation")
    override fun getTemperatureStrategy(temperaturePerSeason: TemperaturePerSeason): String {
      val resultTemperaturePerSeason = ((
                      temperaturePerSeason.temperatureMonthOne +
                      temperaturePerSeason.temperatureMonthTwo +
                      temperaturePerSeason.temperatureMonthThree) / 3).roundToInt()

        return "$resultTemperaturePerSeason°"
    }
}