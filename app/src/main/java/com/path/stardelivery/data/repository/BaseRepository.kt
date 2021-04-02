package com.path.stardelivery.data.repository

import androidx.lifecycle.liveData
import com.path.stardelivery.data.remote.Result
import kotlinx.coroutines.Dispatchers

open class BaseRepository {

    fun <T> performGetOperation(
        databaseQuery: suspend () -> T,
        networkCall: (suspend () -> Result<T>)? = null,
        saveCallResult: (suspend (T) -> Unit)? = null
    ) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        val source = Result.Success(databaseQuery.invoke())
        emit(source)

        if (networkCall != null) {
            when(val responseStatus = networkCall.invoke()) {
                is Result.Success -> saveCallResult?.invoke(responseStatus.data!!)
                is Result.Error -> {
                    emit(Result.Error(responseStatus.code, responseStatus.exception))
                    emit(source)
                }
            }
        }
    }
}