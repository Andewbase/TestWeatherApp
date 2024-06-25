package com.example.weathercompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.weathercompose.screen.main.MainViewModel
import com.example.weathercompose.screen.settings.SettingsScreen
import com.example.weathercompose.screen.settings.SettingsScreenViewModel
import com.example.weathercompose.screen.settings.dialog.SettingsDialogViewModel

@Composable
fun AppNavigation(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = AppScreens.Main.name
    ){
        composable(route = AppScreens.Main.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
        }
        composable(route = AppScreens.Settings.name){
            val settingsScreenViewModel = hiltViewModel<SettingsScreenViewModel>()
            val allCity by settingsScreenViewModel.allCity.collectAsState(initial = emptyList())
            SettingsScreen(allCity = allCity, navController = navController)
        }
        dialog(route = AppScreens.SettingsDialog.name){
            val settingsDialogViewModel = hiltViewModel<SettingsDialogViewModel>()

        }
    }
}