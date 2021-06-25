package com.alvaromr.marvel.interactor

sealed class Resource<T> {
    object Loading : Resource<Nothing>()
    class Error(val type: ErrorType) : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
}