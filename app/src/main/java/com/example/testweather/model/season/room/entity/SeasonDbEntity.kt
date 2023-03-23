package com.example.testweather.model.season.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testweather.model.season.entities.Season
import com.example.testweather.model.season.entities.TemperaturePerSeason

@Entity(tableName = "season")
data class SeasonDbEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo (name = "name_season") val name: String,
    @ColumnInfo (name = "name_month_one") val nameMonthOne: String,
    @ColumnInfo (name = "name_month_two") val nameMonthTwo: String,
    @ColumnInfo (name = "name_month_three") val nameMonthThree: String,
    @ColumnInfo (name = "temperature_month_one") val temperatureMonthOne: Double,
    @ColumnInfo (name = "temperature_month_two") val temperatureMonthTwo: Double,
    @ColumnInfo (name = "temperature_month_three") val temperatureMonthThree: Double
){
    fun toTemperaturePerSeason(): TemperaturePerSeason = TemperaturePerSeason(
        nameSeason = name,
        temperatureMonthOne = temperatureMonthOne,
        temperatureMonthTwo = temperatureMonthTwo,
        temperatureMonthThree = temperatureMonthThree
    )

    fun toSeason(): Season = Season(
        name = name,
        nameMonthOne = nameMonthOne,
        nameMonthTwo = nameMonthTwo,
        nameMonthThree = nameMonthThree,
        temperatureMonthOne = temperatureMonthOne,
        temperatureMonthTwo = temperatureMonthTwo,
        temperatureMonthThree = temperatureMonthThree
    )

    companion object{
        fun createSeason(season: Season) = SeasonDbEntity(
            name = season.name,
            nameMonthOne = season.nameMonthOne,
            nameMonthTwo = season.nameMonthTwo,
            nameMonthThree = season.nameMonthThree,
            temperatureMonthOne = season.temperatureMonthOne,
            temperatureMonthTwo = season.temperatureMonthTwo,
            temperatureMonthThree = season.temperatureMonthThree
        )
    }
}
