package com.path.stardelivery.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.path.stardelivery.data.entities.Station

@Dao
interface StationDao {

    @Query("SELECT * FROM station")
    suspend fun getAllStations() : List<Station>

    @Query("SELECT * FROM station WHERE name = :name")
    suspend fun getStation(name: String): Station

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stations: List<Station>)
}