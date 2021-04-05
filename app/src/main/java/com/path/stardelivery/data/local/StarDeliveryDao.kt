package com.path.stardelivery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.entities.SpaceshipWithStation
import com.path.stardelivery.data.entities.Station

@Dao
interface StarDeliveryDao {

    // region Station Operations
    @Query("SELECT * FROM station")
    suspend fun getAllStations(): List<Station>

    @Query("SELECT * FROM station WHERE name = :name")
    suspend fun getStation(name: String): Station

    @Query("UPDATE station SET isFavorite= :isFavourite WHERE id= :id")
    suspend fun updateStationFavourite(id: Int, isFavourite: Boolean)

    @Query("UPDATE station SET need= :need WHERE id= :stationId")
    suspend fun updateStationNeed(stationId: Int, need: Long)

    @Query("SELECT * FROM station WHERE isFavorite = 1 ")
    suspend fun getFavouriteStations(): List<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStations(stations: List<Station>)

    @Query("DELETE FROM station")
    suspend fun deleteStations()

    //endregion

    // region StarShip

    @Query("SELECT * FROM spaceShip LIMIT 1")
    suspend fun getSpaceShip(): SpaceshipWithStation?

    @Query("UPDATE spaceShip SET health= :health WHERE ROWID=:id")
    suspend fun updateSpaceshipHealth(id: Int, health: Int)

    @Query("UPDATE spaceShip SET speed= :speed, capacity= :capacity WHERE ROWID=:id")
    suspend fun spaceshipTravel(id: Int, speed: Long, capacity: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpaceShip(spaceShip: SpaceShip)

    @Query("DELETE FROM spaceship")
    suspend fun deleteSpaceShip()

    // endregion
}