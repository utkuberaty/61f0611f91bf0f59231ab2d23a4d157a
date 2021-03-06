package com.path.stardelivery.ui.create_ship

import androidx.lifecycle.ViewModel
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.repository.SpaceShipRepository
import com.path.stardelivery.data.repository.StationRepository

class CreateShipViewModel(
    private val spaceShipRepository: SpaceShipRepository,
    private val stationRepository: StationRepository
) : ViewModel() {

    fun saveSpaceship(spaceShip: SpaceShip) = spaceShipRepository.insertSpaceShip(spaceShip)

    fun getAllStations() = stationRepository.getAllStations()
}