package com.path.stardelivery.data.repository

import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.local.StarDeliveryDao

class SpaceShipRepository(
    private val localDataSource: StarDeliveryDao
) : BaseRepository() {

    fun insertSpaceShip(spaceShip: SpaceShip) = performGetOperation({
        localDataSource.insertSpaceShip(spaceShip)
    })

    suspend fun getSpaceShip() = localDataSource.getSpaceShip()

    suspend fun updateSpaceshipHealth(id: Int, health: Int) =
        localDataSource.updateSpaceshipHealth(id, health)

    suspend fun updateSpaceshipTravel(id: Int, speed: Long, capacity: Long) =
        localDataSource.spaceshipTravel(id, speed, capacity)

    suspend fun deleteSpaceShip() {
        localDataSource.deleteSpaceShip()
        localDataSource.deleteStations()
    }
}