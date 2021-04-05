package com.path.stardelivery.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "station", indices = [Index(value = ["name"], unique = true)])
data class Station(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val coordinateX: Double = 0.0,
    val coordinateY: Double = 0.0,
    val capacity: Int = 0,
    val stock: Int = 0,
    val need: Int = 0,
    var isFavorite: Int = 0
) {
    val favourite
    get() =  isFavorite != 0
}
