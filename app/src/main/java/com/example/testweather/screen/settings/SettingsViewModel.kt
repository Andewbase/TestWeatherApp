package com.example.testweather.screen.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.model.city.CityRepository
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import com.example.testweather.util.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val cityRepository: CityRepository): ViewModel() {

    private val _cityAndSeasonInfo: MutableLiveData<List<CityAndTemperature>> = MutableLiveData()
    val cityAndSeasonInfo = _cityAndSeasonInfo.share()

    init {
        viewModelScope.launch {
            cityRepository.getAllCityAndSeason().collect{
                _cityAndSeasonInfo.value = it
            }
        }
    }

}