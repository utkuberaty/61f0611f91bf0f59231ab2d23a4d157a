package com.path.stardelivery.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "station")
data class SpaceShip(
    @PrimaryKey val name: String = "",
    val speed: Int,
    val capacity: Int,
    val stability: Int,
    val location: Pair<Double,Double> = 0.0 to 0.0
)
