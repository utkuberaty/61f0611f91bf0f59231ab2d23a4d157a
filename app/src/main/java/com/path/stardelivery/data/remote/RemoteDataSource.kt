package com.path.stardelivery.data.remote

class RemoteDataSource(private val stationsService: StationsService): Call() {
    suspend fun getAllStations() = call { stationsService.getAllStations() }
}