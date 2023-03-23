package com.example.testweather.util.factory.type

import com.example.testweather.util.Const.SMALL_CITY

class SmallCity: TypeCity {
    override fun getTypeCity(): String {
        return SMALL_CITY
    }
}