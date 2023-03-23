package com.example.testweather.model.cityandseason.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.testweather.model.cityandseason.room.entities.CityAndSeasonDbEntity

@Dao
interface CityAndSeasonDao {

    @Insert(entity = CityAndSeasonDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCityAndSeason(cityAndSeasonDbEntity: CityAndSeasonDbEntity)

}