package com.example.testweather.model.city.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testweather.model.city.entities.City
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "city")
data class CityDbEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo (name = "name_city") val name: String,
    @ColumnInfo (name = "type_city") val type: Int,
    @ColumnInfo (name = "timestamp") val timestamp: String
){
    fun toCity(): City =
        City(
            name = name,
            type = type
        )

    companion object{
        fun createCity(city: City) = CityDbEntity(
            name = city.name,
            type = city.type,
            timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }
}