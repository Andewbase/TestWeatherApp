package com.example.testweather.utils

import com.example.testweather.model.city.room.entities.CityDbEntity
import com.example.testweather.model.cityandseason.room.entities.CityAndSeasonDbEntity
import com.example.testweather.model.season.room.entity.SeasonDbEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun createCityDbEntity(
    name: String = "Москва",
    type: Int = 3,
    timestamp: String = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
) = CityDbEntity(
    name = name,
    type = type,
    timestamp = timestamp
)

fun createSeasonDbEntity(
    name: String = "Весна",
    nameMonthOne: String = "Март",
    nameMonthTwo: String = "Апрель",
    nameMonthThree: String = "Май",
    temperatureMonthOne: Double = 22.0,
    temperatureMonthTwo: Double = 23.4,
    temperatureMonthThree: Double = 25.8
) = SeasonDbEntity(
    name = name,
    nameMonthOne = nameMonthOne,
    nameMonthTwo = nameMonthTwo,
    nameMonthThree = nameMonthThree,
    temperatureMonthOne = temperatureMonthOne,
    temperatureMonthTwo = temperatureMonthTwo,
    temperatureMonthThree = temperatureMonthThree
)

fun createCityAndSeasonDbEntity(
    cityName: String = "Москва",
    seasonName: String = "Весна",
    timestamp: String = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
) = CityAndSeasonDbEntity(
    cityName = cityName,
    seasonName = seasonName,
    timestamp = timestamp
)