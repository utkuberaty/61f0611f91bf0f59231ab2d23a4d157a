package com.path.stardelivery.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "spaceShip")
data class SpaceShip(
    @PrimaryKey val name: String = "",
    val speed: Int,
    val capacity: Int,
    val stability: Int,
    val currentLocationX: Double = 0.0,
    val currentLocationY: Double = 0.0
)
