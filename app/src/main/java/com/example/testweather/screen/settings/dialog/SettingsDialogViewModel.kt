package com.example.testweather.screen.settings.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.cityandseason.CityAndSeasonRepository
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.season.SeasonRepository
import com.example.testweather.model.season.entities.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsDialogViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val seasonRepository: SeasonRepository,
    private val cityAndSeasonRepository: CityAndSeasonRepository
): ViewModel() {

    fun addCity(city: City){
        viewModelScope.launch {
            try {
                cityRepository.addCity(city)
            }catch (e: RuntimeException){
                cityRepository.updateCity(city)
            }
        }
    }

    fun addSeason(season: Season){
        viewModelScope.launch {
            seasonRepository.addSeason(season)
        }
    }

    fun addCityAndSeasonName(cityNameAndSeasonName: CityNameAndSeasonName){
        viewModelScope.launch {
            cityAndSeasonRepository.addCityAndSeason(cityNameAndSeasonName)
        }
    }

}