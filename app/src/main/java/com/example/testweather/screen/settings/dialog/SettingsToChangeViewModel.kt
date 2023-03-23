package com.example.testweather.screen.settings.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.cityandseason.CityAndSeasonRepository
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.season.SeasonRepository
import com.example.testweather.model.season.entities.Season
import com.example.testweather.util.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsToChangeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val seasonRepository: SeasonRepository,
    private val cityAndSeasonRepository: CityAndSeasonRepository
): ViewModel() {

    private val _cityAndTemperature: MutableLiveData<CityAndTemperature> = MutableLiveData()
    val cityAndTemperature = _cityAndTemperature.share()

    fun getCityAndTemperature(nameCity:String){
        viewModelScope.launch {
            cityRepository.getAllCityAndTemperature(nameCity).collect{
                _cityAndTemperature.value = it
            }
        }
    }

    fun updateCityInfo(city: City) {
        viewModelScope.launch {
            cityRepository.updateCity(city)
        }
    }

    fun saveSeason(season: Season){
        viewModelScope.launch {
            seasonRepository.addSeason(season)
        }
    }

    fun saveCityAndSeason(cityNameAndSeasonName: CityNameAndSeasonName){
        viewModelScope.launch {
            cityAndSeasonRepository.addCityAndSeason(cityNameAndSeasonName)
        }
    }

}