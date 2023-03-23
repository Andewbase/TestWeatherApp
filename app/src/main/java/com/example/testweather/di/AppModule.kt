package com.example.testweather.di

import android.content.Context
import androidx.room.Room
import com.example.testweather.model.city.room.CityDao
import com.example.testweather.model.cityandseason.room.CityAndSeasonDao
import com.example.testweather.model.room.AppDatabase
import com.example.testweather.model.season.room.SeasonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//TODO #1 Lazy singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db_weather.db")
            .build()

    @Provides
    @Singleton
    fun provideCityDao(appDataBase: AppDatabase): CityDao {
        return appDataBase.getCityDao()
    }

    @Provides
    @Singleton
    fun provideSeasonDao(appDataBase: AppDatabase): SeasonDao {
        return appDataBase.getSeasonDao()
    }

    @Provides
    @Singleton
    fun provideCityAndSeasonDao(appDataBase: AppDatabase): CityAndSeasonDao {
        return appDataBase.getCityAndSeason()
    }

}