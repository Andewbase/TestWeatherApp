package com.example.testweather.util.factory.type

import com.example.testweather.util.Const.LARGE_CITY

class LargeCity: TypeCity {
    override fun getTypeCity(): String {
        return LARGE_CITY
    }
}