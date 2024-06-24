package com.example.weathercompose.data.cache.di

import android.content.Context
import androidx.room.Room
import com.example.weathercompose.data.cache.WeatherDataBase
import com.example.weathercompose.data.cache.city.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            WeatherDataBase::class.java,
            "weather_database"
        ).build()

    @Provides
    @Singleton
    fun provideCityDao(appDataBase: WeatherDataBase): CityDao =
        appDataBase.getCityDao()

}