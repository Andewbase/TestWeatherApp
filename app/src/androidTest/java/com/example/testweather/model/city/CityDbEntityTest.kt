package com.example.testweather.model.city

import com.example.testweather.model.city.entities.City
import com.example.testweather.model.city.room.entities.CityDbEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CityDbEntityTest {

    @Test
    fun toCityAppEntity(){
        val cityEntity = CityDbEntity(
            name = "Москва",
            type = 3,
            timestamp =  LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )

        val inAppEntity = cityEntity.toCity()

        val expectedInAppEntity = City(
            name = "Москва",
            type = 3
        )

        assertEquals(expectedInAppEntity, inAppEntity)
    }

}