package com.path.stardelivery.data.remote

import retrofit2.Response

abstract class Call {

    protected suspend fun <T> call(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Result.Success(body)
            } else Result.Error(response.code(), response.message())
        } catch (e: Exception) {
            Result.Error(-1, e.message ?: e.toString())
        }
    }
}