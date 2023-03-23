package com.example.testweather.model.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.testweather.model.settings.AppSettings.Companion.NO_SEASON_TYPE
import com.example.testweather.model.settings.AppSettings.Companion.NO_TEMPERATURE_TYPE
import com.example.testweather.model.settings.SharedPreferencesAppSettings.PreferencesKeys.PREF_CURRENT_SEASON_TYPE
import com.example.testweather.model.settings.SharedPreferencesAppSettings.PreferencesKeys.PREF_CURRENT_TEMPERATURE_TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class SharedPreferencesAppSettings @Inject constructor(
   @ApplicationContext appContext: Context
): AppSettings {

    private val dataStorePreferences = appContext.dataStore

    override fun getCurrentTemperatureAndSeasonOfType(): Flow<TemperatureAndSeasonOfTypePreferences> =
        dataStorePreferences.data.catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }.map { preferences ->
            val tempOfType = preferences[PREF_CURRENT_TEMPERATURE_TYPE] ?: NO_TEMPERATURE_TYPE
            val seasonOfType = preferences[PREF_CURRENT_SEASON_TYPE] ?: NO_SEASON_TYPE
            TemperatureAndSeasonOfTypePreferences(tempOfType, seasonOfType)
        }

    override suspend fun setCurrentTemperatureOfType(tempOfType: String){
        dataStorePreferences.edit { preferences ->
            preferences[PREF_CURRENT_TEMPERATURE_TYPE] = tempOfType
        }
    }

    override suspend fun setCurrentSeasonOfType(seasonType: String) {
        dataStorePreferences.edit { preferences ->
            preferences[PREF_CURRENT_SEASON_TYPE] = seasonType
        }
    }

    private object PreferencesKeys {
        val PREF_CURRENT_TEMPERATURE_TYPE = stringPreferencesKey("currentTemperatureOfType")
        val PREF_CURRENT_SEASON_TYPE = stringPreferencesKey("currentSeasonOfType")
    }

}