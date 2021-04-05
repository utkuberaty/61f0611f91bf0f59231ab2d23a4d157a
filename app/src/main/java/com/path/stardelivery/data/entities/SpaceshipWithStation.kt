package com.path.stardelivery.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SpaceshipWithStation(
    @Embedded val spaceShip: SpaceShip,
    @Relation(
        parentColumn = "currentStationId",
        entityColumn = "id"
    )
    val currentStation: Station
)