package com.example.weathercompose.data

import com.example.weathercompose.data.cache.city.entity.CityDBO
import com.example.weathercompose.domain.City

class Mapper {

    fun cityDBOtoCity(cityDBO: CityDBO): City{
        return City(
            id = cityDBO.id,
            name = cityDBO.name,
            type = getTypeCity(cityDBO.type)
        )
    }


    private fun getTypeCity(type: Int): String{
        return when(type){
            1 -> "Маленький"
            2 -> "Средний"
            3 -> "Большой"
            else -> "Тип города не установлен"
        }
    }

}