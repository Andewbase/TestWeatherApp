package com.example.testweather.screen.settings.dialog

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testweather.R
import com.example.testweather.databinding.DialogFragmentSettingsBinding
import com.example.testweather.model.city.entities.City
import com.example.testweather.model.cityandseason.entity.CityNameAndSeasonName
import com.example.testweather.model.season.entities.Season
import com.example.testweather.screen.base.BaseDialogFragment
import com.example.testweather.util.Const.AUTUMN
import com.example.testweather.util.Const.SET_THE_TEMPERATURE
import com.example.testweather.util.Const.SPRING
import com.example.testweather.util.Const.SUMMER
import com.example.testweather.util.Const.WINTER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsDialog: BaseDialogFragment<DialogFragmentSettingsBinding>(DialogFragmentSettingsBinding::inflate) {

    private val viewModel by viewModels<SettingsDialogViewModel>()

    private val numberType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED

    private val textType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

    private  var typeCity: Int = 0

    private lateinit var nameSeason: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editCity.setRawInputType(textType)
            editMothsOne.setRawInputType(numberType)
            editMothsTwo.setRawInputType(numberType)
            editMothsThree.setRawInputType(numberType)
        }

        binding.backButton.setOnClickListener {
            goSettingsFragment()
        }

        initTypeCity()
        initSeason()

        binding.btnToChangeWeather.setOnClickListener {
            saveCityAndSeason()
        }
    }

    private fun saveCityAndSeason() {
        val nameCityValue = binding.editCity.text.toString().trim()
        if (nameCityValue.isEmpty()) {
            binding.editCity.error = WRITE_DOWN_THE_NAME_CITY
            return
        }

        val typeCityValue = typeCity

        val nameMonthOneValue = binding.tvMothsOne.text.toString()
        val nameMonthTwoValue = binding.tvMothsTwo.text.toString()
        val nameMonthThreeValue = binding.tvMothsThree.text.toString()
        val temperatureForOneMonthValue = binding.editMothsOne.text.toString().trim()
        if (temperatureForOneMonthValue.isEmpty()) {
            binding.editMothsOne.error = SET_THE_TEMPERATURE
            return
        }
        val temperatureForTwoMonthValue = binding.editMothsTwo.text.toString().trim()
        if (temperatureForTwoMonthValue.isEmpty()) {
            binding.editMothsTwo.error = SET_THE_TEMPERATURE
            return
        }
        val temperatureForThreeMonthValue = binding.editMothsThree.text.toString().trim()
        if (temperatureForThreeMonthValue.isEmpty()) {
            binding.editMothsThree.error = SET_THE_TEMPERATURE
            return
        }

        if (this::nameSeason.isInitialized) {

            val city = City(
                name = nameCityValue,
                type = typeCityValue
            )
            viewModel.addCity(city)
            val season = Season(
                name = nameSeason,
                nameMonthOne = nameMonthOneValue,
                nameMonthTwo = nameMonthTwoValue,
                nameMonthThree = nameMonthThreeValue,
                temperatureMonthOne = temperatureForOneMonthValue.toDouble(),
                temperatureMonthTwo = temperatureForTwoMonthValue.toDouble(),
                temperatureMonthThree = temperatureForThreeMonthValue.toDouble()
            )
            viewModel.addSeason(season)

            val cityAndSeason = CityNameAndSeasonName(
                nameCityValue,
                nameSeason
            )

            viewModel.addCityAndSeasonName(cityAndSeason)

            goSettingsFragment()

        } else {
            Toast.makeText(requireContext(), CHOOSE_SEASON, Toast.LENGTH_SHORT).show()
        }
    }

    private fun goSettingsFragment(){
        findNavController().navigate(R.id.action_settingsDialog_to_settingsFragment)
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
        private const val WRITE_DOWN_THE_NAME_CITY = "Заполинте графу имя города!"
        private const val CHOOSE_SEASON = "Выберите сезон"
    }

}