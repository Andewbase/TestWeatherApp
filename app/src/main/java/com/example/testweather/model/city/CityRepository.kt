package com.example.testweather.model.city

import androidx.lifecycle.LiveData
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun addCity(city: City)

    suspend fun updateCity(city: City)

    fun getAllCity(): LiveData<List<City>>

    fun getAllCityAndSeason(): Flow<List<CityAndTemperature>>

    fun getAllCityAndTemperature(nameCity: String): Flow<CityAndTemperature>

}