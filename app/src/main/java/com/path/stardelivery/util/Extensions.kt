package com.path.stardelivery.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun <T : ViewModel> ViewModelStoreOwner.getViewModelViaKoin(
    clazz: Class<T>,
    qualifier: Qualifier? = null,
    parameters: ParametersDefinition? = null
): T {
    return ViewModelCompat.getViewModel(
        clazz = clazz,
        qualifier = qualifier,
        parameters = parameters,
        owner = this
    )
}