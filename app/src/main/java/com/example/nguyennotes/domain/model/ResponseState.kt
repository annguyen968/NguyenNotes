package com.example.nguyennotes.domain.model

sealed class ResponseState<T>  (val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): ResponseState<T>(data = data)
    class Success<T>(data: T): ResponseState<T>(data=data)
    class Error<T>(message: String, data: T? = null): ResponseState<T>(data = data, message = message)
}