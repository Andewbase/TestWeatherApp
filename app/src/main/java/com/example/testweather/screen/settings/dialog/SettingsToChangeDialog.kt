package com.example.testweather.screen.settings.dialog

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testweather.R
import com.example.testweather.databinding.DialogFragmentSettingsBinding
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.season.entities.Season
import com.example.testweather.screen.base.BaseDialogFragment
import com.example.testweather.util.Const
import com.example.testweather.util.Const.AUTUMN
import com.example.testweather.util.Const.SPRING
import com.example.testweather.util.Const.SUMMER
import com.example.testweather.util.Const.WINTER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsToChangeDialog: BaseDialogFragment<DialogFragmentSettingsBinding>(DialogFragmentSettingsBinding::inflate) {

    private val viewModel by viewModels<SettingsToChangeViewModel>()

    private val safeArgs: SettingsToChangeDialogArgs by navArgs()

    private val textType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
    private val numberType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED

    private var typeCity: Int = 0

    private lateinit var nameSeason: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editCity.setRawInputType(textType)
            editMothsOne.setRawInputType(numberType)
            editMothsTwo.setRawInputType(numberType)
            editMothsThree.setRawInputType(numberType)
            editCity.isEnabled = false
            edLayout.setHint(R.string.name_city)
            btnToChangeWeather.text = CHANGE
        }

        val nameCity = safeArgs.nameCity

        viewModel.getCityAndTemperature(nameCity)

        viewModel.cityAndTemperature.observe(viewLifecycleOwner){ cityAndTemperatureInfo ->
            binding.apply {
                editCity.setText(cityAndTemperatureInfo.city.name)
                val typeCityValue = cityAndTemperatureInfo.city.type
                initTypeCityGetInfo(typeCityValue)
                val  nameSeasonValue = cityAndTemperatureInfo.temperaturePerSeason.first().nameSeason
                initSeasonGetInfo(nameSeasonValue)

                editMothsOne.setText(cityAndTemperatureInfo.temperaturePerSeason.first().temperatureMonthOne.toString().substringBefore("."))
                editMothsTwo.setText(cityAndTemperatureInfo.temperaturePerSeason.first().temperatureMonthTwo.toString().substringBefore("."))
                editMothsThree.setText(cityAndTemperatureInfo.temperaturePerSeason.first().temperatureMonthThree.toString().substringBefore("."))


            }
        }

        initTypeCity()
        initSeason()

        binding.backButton.setOnClickListener {
            goSettingsFragment()
        }

        binding.btnToChangeWeather.setOnClickListener {
            saveCityAndSeason(nameCity)
        }
    }

    private fun saveCityAndSeason(nameCity: String) {
        val typeCityValue = typeCity

        val nameSeasonValue = nameSeason
        val nameMonthOneValue = binding.tvMothsOne.text.toString()
        val nameMonthTwoValue = binding.editMothsOne.text.toString()
        val nameMonthThreeValue = binding.editMothsTwo.text.toString()
        val temperatureForOneMonthValue = binding.editMothsOne.text.toString().trim()
        if (temperatureForOneMonthValue.isEmpty()) {
            binding.editMothsOne.error = Const.SET_THE_TEMPERATURE
            return
        }
        val temperatureForTwoMonthValue = binding.editMothsTwo.text.toString().trim()
        if (temperatureForTwoMonthValue.isEmpty()) {
            binding.editMothsTwo.error = Const.SET_THE_TEMPERATURE
            return
        }
        val temperatureForThreeMonthValue = binding.editMothsThree.text.toString().trim()
        if (temperatureForThreeMonthValue.isEmpty()) {
            binding.editMothsThree.error = Const.SET_THE_TEMPERATURE
            return
        }

        val city = City(
            name = nameCity,
            type = typeCityValue
        )
        viewModel.updateCityInfo(city)

        val season = Season(
            name = nameSeasonValue,
            nameMonthOne = nameMonthOneValue,
            nameMonthTwo = nameMonthTwoValue,
            nameMonthThree = nameMonthThreeValue,
            temperatureMonthOne = temperatureForOneMonthValue.toDouble(),
            temperatureMonthTwo = temperatureForTwoMonthValue.toDouble(),
            temperatureMonthThree = temperatureForThreeMonthValue.toDouble()
        )
        viewModel.saveSeason(season)

        val cityAndSeason = CityNameAndSeasonName(
            nameCity,
            nameSeason
        )
        viewModel.saveCityAndSeason(cityAndSeason)

        goSettingsFragment()
    }

    private fun goSettingsFragment(){
        findNavController().navigate(R.id.action_settingsToChangeDialog_to_settingsFragment)
    }

    private fun initTypeCityGetInfo(typeCity: Int){
        binding.apply {
            when(typeCity){
                1 -> typeCitySmall.isChecked = true
                2 -> typeCityMedium.isChecked = true
                3 -> typeCityLarge.isChecked = true
            }
        }
    }

    private fun initSeasonGetInfo(nameSeason: String){
        binding.apply {
            when(nameSeason){
                SPRING -> springSeasonSettings.isChecked = true
                SUMMER -> summerSeasonSettings.isChecked = true
                AUTUMN -> autumnSeasonSettings.isChecked = true
                WINTER -> winterSeasonSettings.isChecked = true
            }
        }
    }

    private fun initTypeCity() {
        binding.radioGroupTypeCity.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.type_city_small -> typeCity = 1
                R.id.type_city_medium -> typeCity = 2
                R.id.type_city_large -> typeCity = 3
            }
        }
    }

    private fun initSeason() {
        binding.radioGroupSeasonSettings.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.spring_season_settings -> {
                    binding.tvMothsOne.setText(R.string.march)
                    binding.tvMothsTwo.setText(R.string.april)
                    binding.tvMothsThree.setText(R.string.may)
                    nameSeason = SPRING
                }
                R.id.summer_season_settings -> {
                    binding.tvMothsOne.setText(R.string.june)
                    binding.tvMothsTwo.setText(R.string.july)
                    binding.tvMothsThree.setText(R.string.august)
                    nameSeason = SUMMER
                }
                R.id.autumn_season_settings -> {
                    binding.tvMothsOne.setText(R.string.september)
                    binding.tvMothsTwo.setText(R.string.october)
                    binding.tvMothsThree.setText(R.string.november)
                    nameSeason = AUTUMN
                }
                R.id.winter_season_settings -> {
                    binding.tvMothsOne.setText(R.string.december)
                    binding.tvMothsTwo.setText(R.string.january)
                    binding.tvMothsThree.setText(R.string.february)
                    nameSeason = WINTER
                }
            }
        }
    }

   private companion object{
       private const val CHANGE = "Изменить"
   }

}