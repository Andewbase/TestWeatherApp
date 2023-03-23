package com.example.testweather.screen.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testweather.databinding.ItemMainBinding
import com.example.testweather.model.cityandseason.entity.CityAndTemperature
import com.example.testweather.util.factory.GetTypeCity

class SettingsAdapter: ListAdapter<CityAndTemperature, SettingsAdapter.SettingsViewHolder>(DiffUtilCallbackSettings) {

    private var onItemClick: OnItemClick? = null

    fun setOnClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

   inner class SettingsViewHolder(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) {

       fun bind(cityAndTemperature: CityAndTemperature){
           with(binding){
               nameCityValue.text = cityAndTemperature.city.name
               typeCityValue.text = GetTypeCity().createTypeCity(cityAndTemperature.city.type).getTypeCity()
               typeSeasonSizeValue.text = cityAndTemperature.temperaturePerSeason.size.toString()

               root.setOnClickListener { onItemClick?.onItemClick(cityAndTemperature) }
           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)

        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val cityWeather = getItem(position)

        holder.bind(cityWeather)
    }

    interface OnItemClick{
        fun onItemClick(cityAndTemperature: CityAndTemperature)
    }

    companion object DiffUtilCallbackSettings: DiffUtil.ItemCallback<CityAndTemperature>(){
        override fun areItemsTheSame(oldItem: CityAndTemperature, newItem: CityAndTemperature): Boolean {
           return oldItem.city.name == newItem.city.name
        }

        override fun areContentsTheSame(oldItem: CityAndTemperature, newItem: CityAndTemperature): Boolean {
            return oldItem == newItem
        }

    }

}
