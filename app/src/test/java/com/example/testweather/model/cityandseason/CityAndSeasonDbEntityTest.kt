package com.example.testweather.model.cityandseason

import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.cityandseason.room.entities.CityAndSeasonDbEntity
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CityAndSeasonDbEntityTest {

    @Test
    fun toCityAndSeasonAppEntity(){
        val cityAndSeasonEntity = CityAndSeasonDbEntity(
           cityName = "Москва",
           seasonName = "Весна",
            timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )

        val expectedInAppEntity = CityNameAndSeasonName(
            cityName = "Москва",
            seasonName = "Весна"
        )

        val inAppEntity = CityAndSeasonDbEntity.createCityAndSeason(expectedInAppEntity)

        Assert.assertEquals(cityAndSeasonEntity, inAppEntity)
    }

}