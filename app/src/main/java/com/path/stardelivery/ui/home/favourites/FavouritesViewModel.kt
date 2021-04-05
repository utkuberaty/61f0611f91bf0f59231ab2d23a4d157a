package com.path.stardelivery.ui.home.favourites

import androidx.lifecycle.ViewModel
import com.path.stardelivery.data.repository.StationRepository

class FavouritesViewModel(private val stationRepository: StationRepository): ViewModel() {

    fun getFavouriteStations() = stationRepository.favoriteStations()
}