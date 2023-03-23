package com.example.testweather.model.season.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.testweather.model.season.room.entity.SeasonDbEntity

@Dao
interface SeasonDao {

    @Insert(entity = SeasonDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createSeason(seasonDbEntity: SeasonDbEntity)

}