package com.path.stardelivery.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.path.stardelivery.data.entities.SpaceshipWithStation
import com.path.stardelivery.data.entities.Station
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.math.pow
import kotlin.math.sqrt

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun <T : ViewModel> ViewModelStoreOwner.getViewModelViaKoin(
    clazz: Class<T>,
    qualifier: Qualifier? = null,
    parameters: ParametersDefinition? = null
): T = ViewModelCompat.getViewModel(
    clazz = clazz,
    qualifier = qualifier,
    parameters = parameters,
    owner = this
)

fun SpaceshipWithStation.distanceToStation(station: Station) = sqrt(
    (station.coordinateX - currentStation.coordinateX).pow(2) + (station.coordinateY - currentStation.coordinateY).pow(
        2
    )
).toInt()
