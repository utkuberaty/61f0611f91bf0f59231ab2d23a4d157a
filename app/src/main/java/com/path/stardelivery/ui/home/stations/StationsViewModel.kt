package com.path.stardelivery.ui.home.stations

import androidx.lifecycle.ViewModel
import com.path.stardelivery.data.repository.SpaceShipRepository
import com.path.stardelivery.data.repository.StationRepository

class StationsViewModel(
    private val stationRepository: StationRepository,
    private val spaceShipRepository: SpaceShipRepository
) : ViewModel() {

    fun getStations() = stationRepository.getAllStations()

    suspend fun updateIsFavourite(id: Int, isFavourite: Boolean) =
        stationRepository.updateStationFavourite(id, isFavourite)

    suspend fun updateStationNeed(stationId: Int, need: Long) =
        stationRepository.updateStationNeed(stationId, need)

    suspend fun getSpaceShip() = spaceShipRepository.getSpaceShip()

    suspend fun updateSpaceshipHealth(id: Int, health: Int) =
        spaceShipRepository.updateSpaceshipHealth(id, health)

    suspend fun updateSpaceshipTravel(id: Int, speed: Long, capacity: Long) =
        spaceShipRepository.updateSpaceshipTravel(id, speed, capacity)

    suspend fun deleteAllTable() {
        spaceShipRepository.deleteSpaceShip()
        stationRepository.deleteStation()
    }

}