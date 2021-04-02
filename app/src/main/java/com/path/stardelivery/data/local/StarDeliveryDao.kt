package com.path.stardelivery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.entities.Station

@Dao
interface StarDeliveryDao {

    // region Station Operations
    @Query("SELECT * FROM station")
    suspend fun getAllStations(): List<Station>

    @Query("SELECT * FROM station WHERE name = :name")
    suspend fun getStation(name: String): Station

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stations: List<Station>)

    @Query("DELETE FROM spaceShip")
    suspend fun deleteStations()

    //endregion

    // region StarShip

    @Query("SELECT * FROM spaceShip WHERE ROWID=1")
    suspend fun getSpaceShip(): SpaceShip

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spaceShip: SpaceShip)

    @Query("DELETE FROM spaceShip")
    suspend fun deleteSpaceShip()

    // endregion
}