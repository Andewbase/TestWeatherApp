package com.example.weathercompose.data

import com.example.weathercompose.data.cache.city.CityDao
import com.example.weathercompose.data.cache.city.entity.CityDBO
import com.example.weathercompose.domain.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherRepository {

    suspend fun saveCity(cityDBO: CityDBO)

    fun getAllCity(): Flow<List<City>>

    @Singleton
    class Base @Inject constructor(
        private val cityDao: CityDao,
        private val mapper: Mapper
    ): WeatherRepository{

        override suspend fun saveCity(cityDBO: CityDBO) {
            cityDao.saveCity(cityDBO)
        }

        override fun getAllCity(): Flow<List<City>> {
           return cityDao.getAllCity().map { list ->
               list.map { cityDBO ->
                   mapper.cityDBOtoCity(cityDBO)
               }
           }
        }

    }

}