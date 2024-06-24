package com.example.weathercompose.data.cache.city.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city",
    indices = [
        Index("name", unique = true)
    ]
)
data class CityDBO(
    @PrimaryKey @ColumnInfo("id") val id: Long = 0,
    @ColumnInfo ("name") val name: String,
    @ColumnInfo ("type") val type: Int,
    @Embedded(prefix = "spring_") val spring: Spring,
    @Embedded(prefix = "summer_") val summer: Summer,
    @Embedded(prefix = "autumn_") val autumn: Autumn,
    @Embedded(prefix = "winter_") val winter: Winter
)

data class Spring(
    val name: String = "Весна",
    val nameMarch: String = "Март",
    val nameApril: String = "Апрель",
    val nameMay: String = "Май",
    val temperatureMonthOne: Double? = null,
    val temperatureMonthTwo: Double? = null,
    val temperatureMonthThree: Double? = null
)

data class Summer(
    val name: String = "Лето",
    val nameJune: String = "Июнь",
    val nameJuly: String = "Июль",
    val nameAugust: String = "Август",
    val temperatureMonthOne: Double? = null,
    val temperatureMonthTwo: Double? = null,
    val temperatureMonthThree: Double? = null
)

data class Autumn(
    val name: String = "Осень",
    val nameSeptember: String = "Сентябрь",
    val nameOctober: String = "Октябрь",
    val nameNovember: String = "Ноябрь",
    val temperatureMonthOne: Double? = null,
    val temperatureMonthTwo: Double? = null,
    val temperatureMonthThree: Double? = null
)

data class Winter(
    val name: String = "Зима",
    val nameDecember: String = "Декабрь",
    val nameJanuary: String = "Январь",
    val nameFebruary: String = "Февраль",
    val temperatureMonthOne: Double? = null,
    val temperatureMonthTwo: Double? = null,
    val temperatureMonthThree: Double? = null
)

