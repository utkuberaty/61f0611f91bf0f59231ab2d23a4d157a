package com.path.stardelivery.data.entities

import android.content.IntentSender
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "station")
data class Station(
    @PrimaryKey val name: String = "",
    val coordinateX: Double = 0.0,
    val coordinateY: Double = 0.0,
    val capacity: Int = 0,
    val stock: Int = 0,
    val need: Int = 0,
    val isFinished: Boolean = false,
    val isFavourite: Boolean = false,
)
