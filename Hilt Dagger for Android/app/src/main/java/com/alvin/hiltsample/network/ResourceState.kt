package com.alvin.hiltsample.network


sealed class ResourceState<out T> {
    data class Success<out T>(val response: T) : ResourceState<T>()
    data class GenericError(val code: Int? = null, val error: ApiError? = null) :
        ResourceState<Nothing>()
    data class GenericErrors(val code: Int? = null, val error: String? = null) :
        ResourceState<Nothing>()
    object Loading : ResourceState<Nothing>()
}