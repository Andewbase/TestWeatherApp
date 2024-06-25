package com.example.weathercompose.navigation

import androidx.annotation.StringRes
import com.example.weathercompose.R

enum class AppScreens(@StringRes val title: Int) {
    Main(R.string.main),
    Settings(R.string.settings),
    SettingsDialog(R.string.settings_dialog)
}