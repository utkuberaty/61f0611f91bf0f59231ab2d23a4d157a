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

        val source = Result.Success(databaseQuery.invoke())

        val isDataNull = when (source.data) {
            is List<*> -> source.data.isNullOrEmpty()
            else -> source.data == null
        }

        if (isDataNull && networkCall != null) {
            when (val responseStatus = networkCall.invoke()) {
                is Result.Success -> {
                    saveCallResult?.invoke(responseStatus.data!!)
                    emit(Result.Success(responseStatus.data!!))
                }
                is Result.Error -> {
                    emit(Result.Error(responseStatus.code, responseStatus.exception))
                }
            }
        } else emit(source)
    }
}