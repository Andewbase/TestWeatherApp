package com.example.testweather.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.cityandseason.CityAndSeasonRepository
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import com.example.testweather.model.settings.TemperatureAndSeasonOfTypePreferences
import com.example.testweather.util.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val cityAndSeasonRepository: CityAndSeasonRepository
): ViewModel() {

    private val _cityAndTemperature: MutableLiveData<CityAndTemperature> = MutableLiveData()
    val cityAndTemperature = _cityAndTemperature.share()

    private val _tempOfType: MutableLiveData<TemperatureAndSeasonOfTypePreferences> = MutableLiveData()
    val tempOfType = _tempOfType.share()

    val getAllCity = cityRepository.getAllCity()

   init {
      viewModelScope.launch {
          cityAndSeasonRepository.getTemperatureOfType().collect{
              _tempOfType.value = it
          }
      }
   }

    fun setTemperatureOfType(tempOfType: String){
        viewModelScope.launch {
            cityAndSeasonRepository.setTemperatureOfType(tempOfType)
        }
    }

    fun setSeasonOfType(seasonType: String){
        viewModelScope.launch {
            cityAndSeasonRepository.setCurrentSeasonOfType(seasonType)
        }
    }

    fun getCityAndTemperature(nameCity:String){
        viewModelScope.launch {
            cityRepository.getAllCityAndTemperature(nameCity).collect{
                _cityAndTemperature.value = it
            }
        }
    }

}