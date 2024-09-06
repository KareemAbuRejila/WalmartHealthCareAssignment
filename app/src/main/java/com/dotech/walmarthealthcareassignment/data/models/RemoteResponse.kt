package com.dotech.walmarthealthcareassignment.data.models

sealed class RemoteResponse<out T> {
    data class Success<T>(val data: T) : RemoteResponse<T>()
    data class Error(val message: String) : RemoteResponse<Nothing>()
    data object Loading : RemoteResponse<Nothing>()
}