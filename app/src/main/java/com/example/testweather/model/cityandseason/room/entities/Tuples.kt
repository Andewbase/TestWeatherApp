package com.example.testweather.model.cityandseason.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.season.room.entity.SeasonDbEntity

data class CityWithSeason(
    @Embedded val cityDbEntity: CityDbEntity,
    @Relation(
        parentColumn = "name_city",
        entityColumn = "name_season",
        associateBy = Junction(
            value =  CityAndSeasonDbEntity::class,
            parentColumn = "city_name",
            entityColumn = "season_name"
        )
    )
    val seasonDbEntity: List<SeasonDbEntity>
)
