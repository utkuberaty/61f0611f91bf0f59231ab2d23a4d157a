package com.path.stardelivery.ui.splash

import androidx.lifecycle.ViewModel
import com.path.stardelivery.data.repository.SpaceShipRepository
import com.path.stardelivery.data.repository.StationRepository

class SplashViewModel(
    private val spaceShipRepository: SpaceShipRepository,
    private val stationRepository: StationRepository
) : ViewModel() {

    suspend fun getSpaceShip() = spaceShipRepository.getSpaceShip()
    fun getStation() = stationRepository.getAllStations()

}