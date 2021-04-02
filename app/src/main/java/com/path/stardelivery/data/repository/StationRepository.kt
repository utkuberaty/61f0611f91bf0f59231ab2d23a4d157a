package com.path.stardelivery.data.repository

import com.path.stardelivery.data.local.StationDao
import com.path.stardelivery.data.remote.RemoteDataSource

class StationRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: StationDao
) : BaseRepository() {

    fun getAllStations() = performGetOperation(
        { localDataSource.getAllStations() },
        { remoteDataSource.getAllStations() },
        { localDataSource.insertAll(it) }
    )

    fun getStation(name: String) = performGetOperation(
        { localDataSource.getStation(name) }
    )

}