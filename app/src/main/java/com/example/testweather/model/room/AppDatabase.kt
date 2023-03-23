package com.example.testweather.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testweather.model.city.room.CityDao
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.room.CityAndSeasonDao
import com.example.testweather.model.cityandseason.room.entities.CityAndSeasonDbEntity
import com.example.testweather.model.season.room.SeasonDao
import com.example.testweather.model.season.room.entity.SeasonDbEntity

@Database(
    version = 1,
    entities = [
        CityDbEntity::class,
        SeasonDbEntity::class,
        CityAndSeasonDbEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCityDao(): CityDao

    abstract fun getSeasonDao(): SeasonDao

    abstract fun getCityAndSeason(): CityAndSeasonDao
}