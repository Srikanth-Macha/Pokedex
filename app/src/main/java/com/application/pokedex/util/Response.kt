package com.application.pokedex.util

sealed class Response<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : Response<T>(data, null)
    class Error<T>(error: String) : Response<T>(error = error)
    class Loading<T>(initialValue: T) : Response<T>(data = initialValue)
}
