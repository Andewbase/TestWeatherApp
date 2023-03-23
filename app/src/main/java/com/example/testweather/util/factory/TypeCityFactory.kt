package com.example.testweather.util.factory

import com.example.testweather.util.factory.type.TypeCity

interface TypeCityFactory {
    fun createTypeCity(idType: Int): TypeCity
}