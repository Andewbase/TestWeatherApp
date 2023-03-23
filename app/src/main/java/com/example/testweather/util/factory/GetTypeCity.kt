package com.example.testweather.util.factory

import com.example.testweather.util.factory.type.*

class GetTypeCity: TypeCityFactory {

    override fun createTypeCity(idType: Int): TypeCity {
      return  when(idType) {
          1 -> SmallCity()
          2 -> MediumCity()
          3 -> LargeCity()
          else -> NotSpecifiedCity()
      }

    }


}