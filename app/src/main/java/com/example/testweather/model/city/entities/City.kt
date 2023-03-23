package com.example.testweather.model.city.entities

import androidx.room.ColumnInfo

data class City(
    @ColumnInfo(name = "name_city")  val name: String,
    @ColumnInfo(name = "type_city") val type: Int
)
