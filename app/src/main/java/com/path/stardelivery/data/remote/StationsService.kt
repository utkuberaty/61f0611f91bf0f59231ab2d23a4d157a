package com.path.stardelivery.data.remote

import com.path.stardelivery.data.entities.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationsService {

    @GET("v3/e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getAllStations() : Response<List<Station>>
}