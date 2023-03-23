package com.example.testweather.model.season

import com.example.testweather.model.season.entities.Season
import com.example.testweather.model.season.room.entity.SeasonDbEntity

interface SeasonRepository {
    suspend fun addSeason(season: Season)
}