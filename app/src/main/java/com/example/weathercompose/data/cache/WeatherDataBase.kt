package com.example.weathercompose.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathercompose.data.cache.city.CityDao
import com.example.weathercompose.data.cache.city.entity.CityDBO

@Database(entities = [CityDBO::class], version = 1)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun getCityDao(): CityDao
}