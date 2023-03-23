package com.example.testweather.model.settings

import com.example.testweather.util.Const.CELSIUS
import com.example.testweather.util.Const.SPRING
import kotlinx.coroutines.flow.Flow

interface AppSettings {

    fun getCurrentTemperatureAndSeasonOfType(): Flow<TemperatureAndSeasonOfTypePreferences>

    suspend fun setCurrentTemperatureOfType(tempOfType: String)

    suspend fun setCurrentSeasonOfType(seasonType: String)

    companion object {
        const val NO_TEMPERATURE_TYPE = CELSIUS
        const val NO_SEASON_TYPE = SPRING
    }
}