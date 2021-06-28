package com.alvaromr.marvel.interactor

sealed class Resource<T> {
    object Loading : Resource<Nothing>()
    data class Error(val type: ErrorType) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
}