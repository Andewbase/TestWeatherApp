package com.example.testweather.model.city.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.room.entities.CityWithSeason
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(entity = CityDbEntity::class)
    suspend fun createCity(cityDbEntity: CityDbEntity)

    @Update(entity = CityDbEntity::class)
    suspend fun updateCity(city: City)

    @Query("SELECT * FROM city")
    fun getAllCity(): LiveData<List<CityDbEntity>>

    @Transaction
    @Query("SELECT * FROM city")
    fun getCityOfSeason(): Flow<List<CityWithSeason>>

    @Transaction
    @Query("SELECT * FROM city WHERE name_city = :nameCity")
    fun getCityByNameOfSeason(nameCity:String): Flow<CityWithSeason>

    @Query("DELETE FROM city WHERE name_city IN(SELECT name_city FROM city ORDER BY timeStamp DESC LIMIT 1 OFFSET 4)")
    suspend fun removeOldCity()
}