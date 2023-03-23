package com.example.testweather.util.decorator


import android.util.Log
import com.example.testweather.util.tempstrategy.GetTemperaturePerSeason

class TemperaturePerSeasonDecorator(private val component: GetTemperaturePerSeason, private val tag: String): GetTemperaturePerSeason {

    override fun getTemperaturePerSeason(): String {
        val result = component.getTemperaturePerSeason()

        Log.d(tag, result)

        return result
    }

}