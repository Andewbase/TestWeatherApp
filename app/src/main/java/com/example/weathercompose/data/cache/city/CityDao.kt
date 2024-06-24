package com.example.weathercompose.data.cache.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathercompose.data.cache.city.entity.CityDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(entity = CityDBO::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(cityDBO: CityDBO)

    @Query("SELECT * FROM city")
    fun getAllCity(): Flow<List<CityDBO>>
}