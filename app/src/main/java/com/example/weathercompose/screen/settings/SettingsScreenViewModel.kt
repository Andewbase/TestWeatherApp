package com.example.weathercompose.screen.settings

import androidx.lifecycle.ViewModel
import com.example.weathercompose.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val allCity = weatherRepository.getAllCity()

}