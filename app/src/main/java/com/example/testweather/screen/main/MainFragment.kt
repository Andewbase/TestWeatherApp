package com.example.testweather.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testweather.R
import com.example.testweather.databinding.FragmentMainBinding
import com.example.testweather.screen.base.BaseFragment
import com.example.testweather.util.Const.AUTUMN
import com.example.testweather.util.Const.CELSIUS
import com.example.testweather.util.Const.FAHRENHEIT
import com.example.testweather.util.Const.KELVIN
import com.example.testweather.util.Const.LOGTEMP
import com.example.testweather.util.Const.SPRING
import com.example.testweather.util.Const.SUMMER
import com.example.testweather.util.Const.WINTER
import com.example.testweather.util.decorator.TemperaturePerSeasonDecorator
import com.example.testweather.util.factory.GetTypeCity
import com.example.testweather.util.tempstrategy.GetTemperaturePerSeasonImpl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTempOfType()
        initSeasonOfType()

        viewModel.getAllCity.observe(viewLifecycleOwner){ list ->
            binding.apply {

                if (list.getOrNull(0) != null) oneCityValue.isEnabled = true
                if (list.getOrNull(1) != null) twoCityValue.isEnabled = true
                if (list.getOrNull(2) != null) threeCityValue.isEnabled = true
                if (list.getOrNull(3) != null) fourCityValue.isEnabled = true

                val cityOne = list.getOrNull(0) ?: cityDef
                val cityTwo = list.getOrNull(1) ?: cityDef
                val cityThree = list.getOrNull(2) ?: cityDef
                val cityFour = list.getOrNull(3) ?: cityDef

                oneCityValue.text = cityOne.name
                twoCityValue.text = cityTwo.name
                threeCityValue.text = cityThree.name
                fourCityValue.text = cityFour.name

                val cityValueOne = list.getOrNull(0)
                val cityValueTwo = list.getOrNull(1)
                val cityValueThree = list.getOrNull(2)
                val cityValueFour = list.getOrNull(3)

                radioGroupCity.setOnCheckedChangeListener { _, checkedId ->
                    when(checkedId){
                        R.id.one_city_value ->  if (cityValueOne != null) viewModel.getCityAndTemperature(list[0].name)
                        R.id.two_city_value -> if (cityValueTwo != null) viewModel.getCityAndTemperature(list[1].name)
                        R.id.three_city_value -> if (cityValueThree != null) viewModel.getCityAndTemperature(list[2].name)
                        R.id.four_city_value -> if (cityValueFour != null) viewModel.getCityAndTemperature(list[3].name)
                    }
                }
            }
        }

        viewModel.cityAndTemperature.observe(viewLifecycleOwner){ cityAndtemp ->
            binding.apply {

                nameCityValue.text = cityAndtemp.city.name
                typeCityValue.text = GetTypeCity().createTypeCity(cityAndtemp.city.type).getTypeCity()

                viewModel.tempOfType.observe(viewLifecycleOwner){ tempOfType -> //TODO #4 Observer

                    val tempPerSeason = cityAndtemp.temperaturePerSeason.firstOrNull{ it.nameSeason == tempOfType.seasonType}
                    if (tempPerSeason != null){
                        typeSeasonValue.text = tempPerSeason.nameSeason

                        val temperatureStrategy = GetTemperaturePerSeasonImpl(tempPerSeason, tempOfType.tempType) //TODO #2 Factory
                        val resultTemperatureStrategy = TemperaturePerSeasonDecorator(temperatureStrategy, LOGTEMP).getTemperaturePerSeason() //TODO #3 Decorator
                        val resultOfSnackbar = GetTemperaturePerSeasonImpl(tempPerSeason, tempOfType.tempType).getTemperaturePerSeason()
                        Snackbar.make(binding.radioGroupTypeOfTemperature, resultOfSnackbar, Snackbar.LENGTH_SHORT).show()

                        temperaturePerSeasonValue.text = resultTemperatureStrategy

                    }else{
                        typeSeasonValue.text = INFORMATION_HAS_NOT_BEEN_ADDED_YET
                        temperaturePerSeasonValue.text = INFORMATION_HAS_NOT_BEEN_ADDED_YET
                    }
                }
            }
        }

        binding.goSettings.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            findNavController().navigate(action)
        }
    }

    private fun initTempOfType(){
        binding.radioGroupTypeOfTemperature.setOnCheckedChangeListener{ _, checkedId ->
            when(checkedId){
                R.id.celsius_type_of_temperature -> viewModel.setTemperatureOfType(CELSIUS)
                R.id.kelvin_type_of_temperature -> viewModel.setTemperatureOfType(KELVIN)
                R.id.fahrenheit_type_of_temperature -> viewModel.setTemperatureOfType(FAHRENHEIT)
            }
        }
    }

    private fun initSeasonOfType(){
        binding.radioGroupTypeOfSeason.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.spring_type -> viewModel.setSeasonOfType(SPRING)
                R.id.summer_type -> viewModel.setSeasonOfType(SUMMER)
                R.id.autumn_type -> viewModel.setSeasonOfType(AUTUMN)
                R.id.winter_type -> viewModel.setSeasonOfType(WINTER)
            }
        }
    }

    private val cityDef = com.example.testweather.model.city.entities.City(
        name = THE_CITY_HAS_NOT_BEEN_ADDED_YET,
        type = 0
    )

    companion object {
        private const val INFORMATION_HAS_NOT_BEEN_ADDED_YET = "Нет информации"
        private const val THE_CITY_HAS_NOT_BEEN_ADDED_YET = "Город ещё не добавлен"
    }

}