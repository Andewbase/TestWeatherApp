package com.example.testweather.model.season

import com.example.testweather.model.season.entities.Season
import com.example.testweather.model.season.room.entity.SeasonDbEntity
import org.junit.Assert
import org.junit.Test

class SeasonDbEntityTest {

    @Test
    fun toSeasonAppEntity(){
        val seasonEntity = SeasonDbEntity(
            name = "Весна",
            nameMonthOne = "Март",
            nameMonthTwo = "Апрель",
            nameMonthThree = "Май",
            temperatureMonthOne = 22.0,
            temperatureMonthTwo = 23.4,
            temperatureMonthThree = 25.8
        )

        val inAppEntity = seasonEntity.toSeason()

        val expectedInAppEntity = Season(
            name = "Весна",
            nameMonthOne = "Март",
            nameMonthTwo = "Апрель",
            nameMonthThree = "Май",
            temperatureMonthOne = 22.0,
            temperatureMonthTwo = 23.4,
            temperatureMonthThree = 25.8
        )

        Assert.assertEquals(expectedInAppEntity, inAppEntity)
    }

}