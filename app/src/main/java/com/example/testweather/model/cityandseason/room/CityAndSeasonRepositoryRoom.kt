package com.example.testweather.model.cityandseason.room

import com.example.testweather.model.cityandseason.CityAndSeasonRepository
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.cityandseason.room.entities.CityAndSeasonDbEntity
import com.example.testweather.model.settings.AppSettings
import com.example.testweather.model.settings.TemperatureAndSeasonOfTypePreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityAndSeasonRepositoryRoom @Inject constructor(
    private val cityAndSeasonDao: CityAndSeasonDao,
    private val appSettings: AppSettings,
): CityAndSeasonRepository {

    override suspend fun addCityAndSeason(cityAndSeasonName: CityNameAndSeasonName){
        createCityAndSeason(cityAndSeasonName)
    }

    override fun getTemperatureOfType(): Flow<TemperatureAndSeasonOfTypePreferences> = appSettings.getCurrentTemperatureAndSeasonOfType()

    override suspend fun setTemperatureOfType(tempOfType: String) {
        appSettings.setCurrentTemperatureOfType(tempOfType)
    }

    override suspend fun setCurrentSeasonOfType(nameSeason: String) {
        appSettings.setCurrentSeasonOfType(nameSeason)
    }

    private suspend fun createCityAndSeason(cityAndSeasonName: CityNameAndSeasonName){
        val entity = CityAndSeasonDbEntity.createCityAndSeason(cityAndSeasonName)
        cityAndSeasonDao.createCityAndSeason(entity)
    }

}