package com.dotech.walmarthealthcareassignment.domain.models


enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class ComponentListState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ComponentListState<T> {
            return ComponentListState(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T?): ComponentListState<T> {
            return ComponentListState(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): ComponentListState<T> {
            return ComponentListState(Status.LOADING, data, null)
        }
    }
}