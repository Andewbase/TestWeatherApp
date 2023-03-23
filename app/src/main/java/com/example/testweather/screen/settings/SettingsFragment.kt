package com.example.testweather.screen.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testweather.databinding.FragmentSettingsBinding
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import com.example.testweather.screen.base.BaseFragment
import com.example.testweather.screen.settings.adapter.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel by viewModels<SettingsViewModel>()

    private val adapter by lazy { SettingsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCityWeather.adapter = adapter

        viewModel.cityAndSeasonInfo.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        adapter.setOnClick(object : SettingsAdapter.OnItemClick{
            override fun onItemClick(cityAndTemperature: CityAndTemperature) {
                val action = SettingsFragmentDirections.actionSettingsFragmentToSettingsToChangeDialog(cityAndTemperature.city.name)
                findNavController().navigate(action)
            }
        })

        binding.btnAddCity.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToSettingsDialog()
            findNavController().navigate(action)
        }

    }

}