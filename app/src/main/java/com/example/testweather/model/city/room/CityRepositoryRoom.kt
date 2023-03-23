package com.example.testweather.model.city.room

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryRoom @Inject constructor(
    private val cityDao: CityDao
): CityRepository {

    override suspend fun addCity(city: City) {
        createCity(city)
        removeOldCity()
    }

    override suspend fun updateCity(city: City) {
        cityDao.updateCity(city)
    }

    override fun getAllCity(): LiveData<List<City>> {
       return cityDao.getAllCity().map { list ->
           list.map { cityDb ->
               cityDb.toCity()
           }
       }
    }

    override fun getAllCityAndSeason(): Flow<List<CityAndTemperature>> {
        return cityDao.getCityOfSeason().map { listCityWithSeason ->
            listCityWithSeason.map { cityWithSeason ->
                CityAndTemperature(
                    city = cityWithSeason.cityDbEntity.toCity(),
                    temperaturePerSeason = cityWithSeason.seasonDbEntity.map { seasonDbEntity ->
                        seasonDbEntity.toTemperaturePerSeason()
                    }
                )
            }
        }
    }

    override fun getAllCityAndTemperature(nameCity: String): Flow<CityAndTemperature>{
        return cityDao.getCityByNameOfSeason(nameCity).map { cityWithSeason ->
                CityAndTemperature(
                    city = cityWithSeason.cityDbEntity.toCity(),
                    temperaturePerSeason = cityWithSeason.seasonDbEntity.map { seasonDbEntity ->
                        seasonDbEntity.toTemperaturePerSeason()
                    }
                )
            }
    }

     private suspend fun removeOldCity() {
        cityDao.removeOldCity()
    }

    private suspend fun createCity(city: City){
        try{
            val entity = CityDbEntity.createCity(city)
            cityDao.createCity(entity)
        }catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }
}