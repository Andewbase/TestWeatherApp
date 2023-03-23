package com.example.testweather.model.cityandseason

import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.settings.TemperatureAndSeasonOfTypePreferences
import kotlinx.coroutines.flow.Flow

interface CityAndSeasonRepository {

    suspend fun addCityAndSeason(cityAndSeasonName: CityNameAndSeasonName)

    fun getTemperatureOfType(): Flow<TemperatureAndSeasonOfTypePreferences>

    suspend fun setTemperatureOfType(tempOfType: String)

    suspend fun setCurrentSeasonOfType(nameSeason: String)
}