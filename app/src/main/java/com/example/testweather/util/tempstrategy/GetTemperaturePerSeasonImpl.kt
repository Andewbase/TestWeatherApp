package com.example.testweather.util.tempstrategy

import com.example.testweather.model.season.entities.TemperaturePerSeason
import com.example.testweather.util.Const.FAHRENHEIT
import com.example.testweather.util.Const.KELVIN
import com.example.testweather.util.tempstrategy.strategy.CelsiusTemperatureStrategy
import com.example.testweather.util.tempstrategy.strategy.FahrenheitTemperatureStrategy
import com.example.testweather.util.tempstrategy.strategy.KelvinTemperatureStrategy
import com.example.testweather.util.tempstrategy.strategy.TemperatureStrategy

class GetTemperaturePerSeasonImpl(private val temperaturePerSeason: TemperaturePerSeason, private val typeOfTemperature: String):
    GetTemperaturePerSeason {

    override fun getTemperaturePerSeason(): String {

        val temperaturePerSeasonStrategy: TemperatureStrategy = when (typeOfTemperature) {
            FAHRENHEIT -> FahrenheitTemperatureStrategy()
            KELVIN -> KelvinTemperatureStrategy()
            else -> CelsiusTemperatureStrategy()
        }

        return temperaturePerSeasonStrategy.getTemperatureStrategy(temperaturePerSeason)
    }

}