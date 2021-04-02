package com.path.stardelivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.entities.Station

@Database(entities = [Station::class, SpaceShip::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StarDeliveryDao
}