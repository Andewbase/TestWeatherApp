package com.example.testweather.model.room

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class CityDaoTest {

    private lateinit var weatherDatabase: AppDatabase

    @Before
    fun initDb(){
        weatherDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
    }

    @After
    fun closeDb(){
        weatherDatabase.close()
    }

    @Test
    fun insertWeatherSaveData(){



    }

}