package com.example.testweather.model.cityandseason.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.season.room.entity.SeasonDbEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(
    tableName = "city_and_season",
    foreignKeys = [
        ForeignKey(
            entity = CityDbEntity::class,
            parentColumns = ["name_city"],
            childColumns = ["city_name"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SeasonDbEntity::class,
            parentColumns = ["name_season"],
            childColumns = ["season_name"],
            onUpdate = ForeignKey.CASCADE,
            /*onDelete = ForeignKey.CASCADE*/
    )
    ],
    primaryKeys = ["city_name", "season_name"]
)
data class CityAndSeasonDbEntity (
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "season_name") val seasonName: String,
    @ColumnInfo (name = "timestamp") val timestamp: String
){
    companion object{
        fun createCityAndSeason(cityAndSeason: CityNameAndSeasonName): CityAndSeasonDbEntity = CityAndSeasonDbEntity(
            cityName = cityAndSeason.cityName,
            seasonName = cityAndSeason.seasonName,
            timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }
}