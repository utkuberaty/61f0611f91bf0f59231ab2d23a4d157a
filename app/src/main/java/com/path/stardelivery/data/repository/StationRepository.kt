package com.path.stardelivery.data.repository

import androidx.lifecycle.liveData
import com.path.stardelivery.data.local.StarDeliveryDao
import com.path.stardelivery.data.remote.RemoteDataSource

class StationRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: StarDeliveryDao
) : BaseRepository() {

    fun getAllStations() = performGetOperation(
        { localDataSource.getAllStations() },
        { remoteDataSource.getAllStations() },
        { localDataSource.insertAllStations(it) }
    )

    fun favoriteStations() = performGetOperation({ localDataSource.getFavouriteStations() })

    suspend fun updateStationFavourite(id: Int, isFavourite: Boolean) =
        localDataSource.updateStationFavourite(id, isFavourite)

    fun getStation(name: String) = performGetOperation({ localDataSource.getStation(name) })

    suspend fun updateStationNeed(stationId: Int, need: Long) =
        localDataSource.updateStationNeed(stationId, need)


    suspend fun deleteStation() = localDataSource.deleteStations()
}