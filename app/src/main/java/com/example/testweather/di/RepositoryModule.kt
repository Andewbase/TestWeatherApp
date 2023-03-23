package com.example.testweather.di

import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.city.room.CityRepositoryRoom
import com.example.testweather.model.cityandseason.CityAndSeasonRepository
import com.example.testweather.model.cityandseason.room.CityAndSeasonRepositoryRoom
import com.example.testweather.model.season.SeasonRepository
import com.example.testweather.model.season.room.SeasonRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsCityRepository(
        cityRepository: CityRepositoryRoom
    ): CityRepository

    @Binds
    abstract fun bindsSeasonRepository(
        seasonRepository: SeasonRepositoryRoom
    ): SeasonRepository

    @Binds
    abstract fun bindsCityAndSeasonRepository(
        cityAndSeasonRepository: CityAndSeasonRepositoryRoom
    ): CityAndSeasonRepository

}