package com.path.stardelivery.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "spaceShip")
data class SpaceShip(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val speed: Long = 0,
    val capacity: Long = 0,
    val stability: Long = 0,
    val health: Int = 100,
    @TypeConverters(Station::class)
    val currentStationId: Int = 1
)
