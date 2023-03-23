package com.example.testweather.util.factory.type

import com.example.testweather.util.Const.NOT_SPECIFIED_CITY

class NotSpecifiedCity: TypeCity {
    override fun getTypeCity(): String {
        return NOT_SPECIFIED_CITY
    }
}