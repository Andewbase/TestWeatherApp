package com.example.testweather.model.season.room


import com.example.testweather.model.season.SeasonRepository
import com.example.testweather.model.season.entities.Season
import com.example.testweather.model.season.room.entity.SeasonDbEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonRepositoryRoom @Inject constructor(
    private val seasonDao: SeasonDao
): SeasonRepository {

    override suspend fun addSeason(season: Season){
        createSeason(season)
    }

    private suspend fun createSeason(season: Season){
        val entity = SeasonDbEntity.createSeason(season)
        seasonDao.createSeason(entity)
    }

}