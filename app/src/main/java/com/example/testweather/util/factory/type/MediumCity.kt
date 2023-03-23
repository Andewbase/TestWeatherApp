package com.example.testweather.util.factory.type

import com.example.testweather.util.Const.MEDIUM_CITY

class MediumCity: TypeCity {
    override fun getTypeCity(): String {
        return MEDIUM_CITY
    }
}